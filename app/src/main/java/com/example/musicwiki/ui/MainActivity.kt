package com.example.musicwiki.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.musicwiki.R
import com.example.musicwiki.adapter.GenreAdapter
import com.example.musicwiki.databinding.ActivityMainBinding
import com.example.musicwiki.util.CustomProgress
import com.example.musicwiki.util.Status
import com.example.musicwiki.util.toastMsg
import com.example.musicwiki.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        viewModel.getTags().observe(this, Observer {
            it?.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                        CustomProgress.instance?.show(this@MainActivity)
                    }
                    Status.SUCCESS -> {
                        CustomProgress.instance?.dismiss()
                        response.data?.let { model ->
                            adapter = GenreAdapter(GenreAdapter.GenreOnClick {
                                val intent =
                                    Intent(this@MainActivity, GenreDetailsActivity::class.java)
                                intent.putExtra("tag", it.name)
                                startActivity(intent)
                            }, model.tags.tag, this@MainActivity)
                            binding.genreGridview.adapter = adapter
                        }
                    }
                    Status.ERROR -> {
                        CustomProgress.instance?.dismiss()
                        toastMsg(response.message.toString())
                    }
                }
            }
        })

    }
}