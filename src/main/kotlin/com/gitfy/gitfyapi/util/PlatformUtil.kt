package com.gitfy.gitfyapi.util

import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.platforms.Github
import com.gitfy.gitfyapi.util.vo.Assets
import com.gitfy.gitfyapi.util.vo.Release
import com.gitfy.gitfyapi.util.vo.RepoDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlatformUtil {

    @Autowired
    private lateinit var redisUtil: RedisUtil


    fun addToRedis(repo: Repo) {
        redisUtil.set(buildKey(repo), getRepoDetail(repo))
    }

    fun getFromRedis(repo: Repo): RepoDetail {
        return redisUtil.get(buildKey(repo)) as RepoDetail
    }

    private fun getRepoDetail(repo: Repo): RepoDetail {
        when (repo.platform) {
            "github" -> return Github(repo.owner, repo.repo).getRepoDetail()
        }
        return RepoDetail(
            Repo("github", "SukiEva", "GitfyApi"), listOf(
                Release(
                    "github.com", "v1.0", "v1.0", "test", false, "null", listOf(
                        Assets("null", "null")
                    )
                )
            ), "null"
        )
    }

    private fun buildKey(repo: Repo): String {
        return "${repo.platform}:${repo.owner}:${repo.repo}"
    }

}