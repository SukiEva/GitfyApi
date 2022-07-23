package com.gitfy.gitfyapi.pojo

import com.gitfy.gitfyapi.util.NoArg
import java.io.Serializable

@NoArg
data class Repo(
    var platform: String,
    var owner: String,
    var repo: String,
): Serializable
