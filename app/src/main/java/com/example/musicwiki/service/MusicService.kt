package com.example.musicwiki.service

import com.example.musicwiki.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET("2.0/")
    suspend fun getGenreList(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String
    ): Response<NetworkGenreModel>

    @GET("2.0/")
    suspend fun getTagInfo(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("api_key") api_key: String,
        @Query("tag") tag: String
    ): Response<TagInfoModel>

    @GET("2.0/")
    suspend fun getAlbumList(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("api_key") api_key: String,
        @Query("tag") tag: String
    ): Response<AlbumModel>

    @GET("2.0/")
    suspend fun getArtistList(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("api_key") api_key: String,
        @Query("tag") tag: String
    ): Response<ArtistModel>

    @GET("2.0/")
    suspend fun getTrackList(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("api_key") api_key: String,
        @Query("tag") tag: String
    ): Response<TrackModel>
}