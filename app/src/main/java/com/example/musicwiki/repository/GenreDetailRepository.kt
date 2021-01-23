package com.example.musicwiki.repository

import com.example.musicwiki.service.MusicService
import javax.inject.Inject

class GenreDetailRepository @Inject constructor(private val service: MusicService) {

    suspend fun getTagInfo(api_key: String, tag: String) = service.getTagInfo(
        "tag.getinfo",
        "json",
        api_key,
        tag
    )

    suspend fun getAlbumList(api_key: String, tag: String) = service.getAlbumList(
        "tag.gettopalbums",
        "json",
        api_key,
        tag
    )

    suspend fun getArtistList(api_key: String, tag: String) = service.getArtistList(
        "tag.gettopartists",
        "json",
        api_key,
        tag
    )

    suspend fun getTrackList(api_key: String, tag: String) = service.getTrackList(
        "tag.gettoptracks",
        "json",
        api_key,
        tag
    )
}