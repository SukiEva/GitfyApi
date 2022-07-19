package com.gitfy.gitfyapi.util

import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.platforms.Github
import com.gitfy.gitfyapi.util.vo.RepoDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlatformUtil {

    @Autowired
    private lateinit var redisUtil: RedisUtil


    fun addToRedis(repo: Repo): Boolean {
        val detail = getRepoDetail(repo) ?: return false
        return redisUtil.set(buildKey(repo), detail)
    }

    fun getFromRedis(repo: Repo): RepoDetail {
        return redisUtil.get(buildKey(repo)) as RepoDetail
    }

    private fun getRepoDetail(repo: Repo): RepoDetail? {
        when (repo.platform) {
            "github" -> {
                val github = Github(repo.owner, repo.repo)
                val detail = github.getRepoDetail()
                if (!github.ifRepoExists) return null
                return detail
            }
        }
        return null
    }

    private fun buildKey(repo: Repo): String {
        return "${repo.platform}:${repo.owner}:${repo.repo}"
    }

}