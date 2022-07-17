package com.gitfy.gitfyapi.pojo

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.gitfy.gitfyapi.util.NoArg


@NoArg
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class Repo(
    var platform: String,
    var owner: String,
    var repo: String,
)
