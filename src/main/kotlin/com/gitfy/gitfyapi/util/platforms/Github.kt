package com.gitfy.gitfyapi.util.platforms

import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.parseArray
import com.alibaba.fastjson2.parseObject
import com.gitfy.gitfyapi.config.PropertyConfig
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.HttpUtil
import com.gitfy.gitfyapi.util.vo.Assets
import com.gitfy.gitfyapi.util.vo.Release
import com.gitfy.gitfyapi.util.vo.RepoDetail


class Github(
    var owner: String, var repo: String
) {

    private val platform = "github"
    private val baseUrl = "https://api.github.com/repos/$owner/$repo"
    private val releaseUrl = "$baseUrl/releases"
    private val readmeUrl = "$baseUrl/readme"
    private val authorization =
        mapOf("Authorization" to "token ${PropertyConfig.github.token}")

    var ifRepoExists = true

    fun getRepoDetail(): RepoDetail {
        return RepoDetail(
            Repo(platform, owner, repo), getReleases(), getReadMe()
        )
    }

    private fun getReleases(): List<Release> {
        val list: MutableList<Release> = mutableListOf()
        val releases = HttpUtil.doGet(releaseUrl, headers = authorization).parseArray()
        if (releases == null) {
            ifRepoExists = false
            return list
        }
        for (i in 0 until releases.size) {
            val release = releases.getJSONObject(i) ?: continue
            list.add(
                Release(
                    release.getString("html_url"),
                    release.getString("tag_name"),
                    release.getString("name"),
                    release.getString("body"),
                    release.getBoolean("prerelease"),
                    release.getString("published_at"),
                    getAssets(release)
                )
            )
        }
        return list
    }

    private fun getAssets(release: JSONObject): List<Assets> {
        val list: MutableList<Assets> = mutableListOf()
        val assets = release.getJSONArray("assets") ?: return list
        for (i in 0 until assets.size) {
            val asset = assets.getJSONObject(i)
            list.add(
                Assets(
                    asset.getString("name"), asset.getString("browser_download_url")
                )
            )
        }
        return list
    }

    private fun getReadMe(): String {
        val readmeJson =
            HttpUtil.doGet(readmeUrl, headers = authorization).parseObject() ?: return ""
        return readmeJson.getString("content") ?: ""
    }

}