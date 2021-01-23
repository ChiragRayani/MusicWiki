package com.example.musicwiki.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.musicwiki.R
import com.example.musicwiki.adapter.AlbumAdapter
import com.example.musicwiki.adapter.ArtistAdapter
import com.example.musicwiki.databinding.FragmentArtistBinding
import com.example.musicwiki.util.CustomProgress
import com.example.musicwiki.util.Status
import com.example.musicwiki.util.toastMsg
import com.example.musicwiki.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentArtist : Fragment() {
    private lateinit var binding:FragmentArtistBinding
    private val artistArgs by navArgs<FragmentArtistArgs>()
    private val viewModel by viewModels<GenreViewModel>()
    private lateinit var adapter: ArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistBinding.inflate(inflater,container,false)

        viewModel.getArtistList(artistArgs.tag).observe(viewLifecycleOwner, Observer {
            it?.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                        CustomProgress.instance?.show(requireActivity())
                    }
                    Status.SUCCESS -> {
                        CustomProgress.instance?.dismiss()
                        response.data?.let {
                            adapter = ArtistAdapter(
                                ArtistAdapter.ArtistOnClick {

                                },
                                it.topartists.artist,
                                requireContext()
                            )
                            binding.artistList.adapter = adapter
                        }
                    }
                    Status.ERROR -> {
                        CustomProgress.instance?.dismiss()
                        requireContext().toastMsg(response.message.toString())
                    }
                }

            }
        })

        return binding.root
    }


}