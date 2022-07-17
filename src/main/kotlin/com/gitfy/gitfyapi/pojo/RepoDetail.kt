package com.gitfy.gitfyapi.pojo

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.gitfy.gitfyapi.util.NoArg
import com.gitfy.gitfyapi.util.Release

@NoArg
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class RepoDetail(
    var repo: Repo,
    var releases: Release,
    var readMe: String,
)
