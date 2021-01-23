package com.example.musicwiki.model

import com.google.gson.annotations.SerializedName

data class TrackModel(
    var tracks: Tracks
) {
    data class Tracks(
        var track: ArrayList<Track>
    ) {
        data class Track(
            var artist: Artist,
            var duration: String,
            var image: List<Image>,
            var mbid: String,
            var name: String,
            var streamable: Streamable,
            var url: String
        ) {
            data class Artist(
                var mbid: String,
                var name: String,
                var url: String
            )

            data class Image(
                @SerializedName("#text")
                var text: String,
                var size: String
            )

            data class Streamable(
                @SerializedName("#text")
                var text: String,
                var fulltrack: String
            )
        }
    }
}