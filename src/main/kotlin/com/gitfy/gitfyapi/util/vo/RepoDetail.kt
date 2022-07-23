package com.gitfy.gitfyapi.util.vo

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.NoArg
import java.io.Serializable

@NoArg
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class RepoDetail(
    var repo: Repo,
    var releases: List<Release>,
    var desp: String,
    var readme: String,
    var updatedAt: String
) : Serializable

@NoArg
data class Release(
    var url: String,
    var tag: String,
    var name: String,
    var body: String,
    var preRelease: Boolean,
    var publishAt: String,
    var assets: List<Assets>
): Serializable

@NoArg
data class Assets(
    var name: String,
    var download: String,
): Serializable