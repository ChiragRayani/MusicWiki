package com.example.musicwiki.model

data class TagInfoModel(
    var tag: Tag
){
    data class Tag(
        var name: String,
        var reach: Int,
        var total: Int,
        var wiki: Wiki
    ){
        data class Wiki(
            var content: String,
            var summary: String
        )
    }
}