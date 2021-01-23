package com.example.musicwiki.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.musicwiki.adapter.AlbumAdapter
import com.example.musicwiki.databinding.FragmentAlbumBinding
import com.example.musicwiki.util.CustomProgress
import com.example.musicwiki.util.Status
import com.example.musicwiki.util.isInternetOn
import com.example.musicwiki.util.toastMsg
import com.example.musicwiki.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAlbum : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private val albumArgs by navArgs<FragmentAlbumArgs>()
    private val viewModel by viewModels<GenreViewModel>()

    private lateinit var adapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        if (requireContext().isInternetOn()) {
            viewModel.getAlbumList(albumArgs.tag).observe(viewLifecycleOwner, Observer {
                it?.let { response ->
                    when (response.status) {
                        Status.LOADING -> {
                            CustomProgress.instance?.show(requireActivity())
                        }
                        Status.SUCCESS -> {
                            CustomProgress.instance?.dismiss()
                            response.data?.let {
                                adapter = AlbumAdapter(
                                    AlbumAdapter.AlbumOnClick {

                                    },
                                    it.albums.album,
                                    requireContext()
                                )
                                binding.albumList.adapter = adapter
                            }
                        }
                        Status.ERROR -> {
                            CustomProgress.instance?.dismiss()
                            requireContext().toastMsg(response.message.toString())
                        }
                    }

                }
            })
        }else requireContext().toastMsg("Please check internet connection!")
        return binding.root
    }


}