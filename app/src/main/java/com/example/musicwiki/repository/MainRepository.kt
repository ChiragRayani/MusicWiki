package com.example.musicwiki.repository

import com.example.musicwiki.service.MusicService
import javax.inject.Inject

class MainRepository @Inject constructor(private val service: MusicService) {

    suspend fun getGenre(api_key: String) = service.getGenreList(
        "chart.gettoptags",
        api_key,
        "json"
    )
}