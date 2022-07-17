package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.mapper.RepoMapper
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.RepoDetail
import com.gitfy.gitfyapi.util.RedisUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class RepoService {

    @Autowired
    private lateinit var repoMapper: RepoMapper

    @Autowired
    private lateinit var redisUtil: RedisUtil

    fun getAllRepos(): List<RepoDetail> {
        val repoDetailList = mutableListOf<RepoDetail>()
        val repoList = repoMapper.getAllRepos()
        for (repo in repoList) {
            val detail =
                redisUtil.get("${repo.platform}:${repo.owner}:${repo.repo}") as RepoDetail
            repoDetailList.add(detail)
        }
        return repoDetailList
    }

    fun getReposByPlatform(platform: String): List<Repo> {
        return repoMapper.getReposByPlatform(platform)
    }

    fun getReposByOwner(platform: String, owner: String): List<Repo> {
        return repoMapper.getReposByOwner(platform, owner)
    }

    fun addRepo(repo: Repo) {
        if (repoMapper.ifRepoExists(repo) != 0) {
            repoMapper.addRepo(repo)
        }
    }

    fun removeRepo(repo: Repo) {
        repoMapper.removeRepo(repo)
    }
}