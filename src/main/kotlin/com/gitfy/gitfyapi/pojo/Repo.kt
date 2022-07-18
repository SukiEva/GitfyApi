package com.gitfy.gitfyapi.pojo

import com.gitfy.gitfyapi.util.NoArg

@NoArg
data class Repo(
    var platform: String,
    var owner: String,
    var repo: String,
)
