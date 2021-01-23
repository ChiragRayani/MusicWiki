package com.example.musicwiki.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(
    var albums: Albums
){
    data class Albums(
        var album: ArrayList<Album>
    ){
        data class Album(
            var artist: Artist,
            var image: List<Image>,
            var mbid: String,
            var name: String,
            var url: String
        ){
            data class Artist(
                var mbid: String,
                var name: String,
                var url: String
            )

            data class Image(
                @SerializedName("#text")
                var text: String?,
                var size: String
            )
        }
    }
}