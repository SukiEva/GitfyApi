package com.gitfy.gitfyapi.util

data class Release(
    var url: String,
    var tag: String,
    var name: String,
    var body: String,
    var preRelease: Boolean,
    var publishAt: String,
    var assets: List<Assets>
)


data class Assets(
    var name: String,
    var download: String,
)