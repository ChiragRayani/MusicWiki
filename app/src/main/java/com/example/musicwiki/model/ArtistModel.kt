package com.example.musicwiki.model

import com.google.gson.annotations.SerializedName

data class ArtistModel(
    var topartists: Topartists
) {
    data class Topartists(
        var artist: ArrayList<Artist>
    ) {
        data class Artist(
            var image: List<Image>,
            var mbid: String,
            var name: String,
            var streamable: String,
            var url: String
        ) {
            data class Image(
                @SerializedName("#text")
                var text: String,
                var size: String
            )
        }
    }
}