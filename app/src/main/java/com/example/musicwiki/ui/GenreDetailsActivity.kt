package com.example.musicwiki.ui

import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.get
import androidx.navigation.ui.setupWithNavController
import com.example.musicwiki.R
import com.example.musicwiki.databinding.ActivityGenreDetailsBinding
import com.example.musicwiki.adapter.SectionsPagerAdapter
import com.example.musicwiki.util.CustomProgress
import com.example.musicwiki.util.Status
import com.example.musicwiki.util.isInternetOn
import com.example.musicwiki.util.toastMsg
import com.example.musicwiki.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenreDetailsBinding
    private val viewModel by viewModels<GenreViewModel>()
    private lateinit var navController: NavController


    private val sectionsPagerAdapter =
        SectionsPagerAdapter(
            this,
            supportFragmentManager
        )
    private var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_genre_details
        )
        bundle = intent.extras

        binding.back.setOnClickListener {
            finish()
        }

        binding.tag.text = bundle?.getString("tag").toString()

        navController = findNavController(R.id.nav_host)
        navController.setGraph(R.navigation.navigation_details, bundle)
        binding.navigation.setupWithNavController(navController)
        binding.navigation.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId, bundle)
            true
        }

        binding.navigation.itemIconTintList = null
        binding.navigation.menu.forEach {
            TooltipCompat.setTooltipText(this.findViewById(it.itemId), null)
        }

        if(isInternetOn()) {
            viewModel.getTagsInfo(bundle?.getString("tag").toString()).observe(this, Observer {
                it?.let { response ->
                    when (response.status) {
                        Status.LOADING -> {
                            CustomProgress.instance?.show(this)
                        }
                        Status.SUCCESS -> {
                            CustomProgress.instance?.dismiss()
                            response.data?.let { model ->
                                binding.content.text = Html.fromHtml(model.tag.wiki.summary)
                            }

                            /*val viewPager: ViewPager = findViewById(R.id.view_pager)*/
                            /* binding.viewPager.adapter = sectionsPagerAdapter
                         binding.tabs.setupWithViewPager(binding.viewPager)*/
                        }
                        Status.ERROR -> {
                            CustomProgress.instance?.dismiss()
                            toastMsg(response.message.toString())
                        }
                    }
                }
            })
        }else toastMsg("Please check internet connection!")
    }
}