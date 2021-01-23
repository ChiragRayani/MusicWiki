package com.example.musicwiki.model

data class NetworkGenreModel(
    var tags: Tags
) {
    data class Tags(
        var tag: ArrayList<Tag>
    ) {
        data class Tag(
            var name: String,
            var reach: String,
            var streamable: String,
            var taggings: String,
            var url: String,
            var wiki: Wiki
        ) {
            data class Wiki(
                var published:String,
                var summary:String,
                var content:String
            )
        }
    }
}