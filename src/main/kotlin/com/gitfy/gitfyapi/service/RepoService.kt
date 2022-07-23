package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.mapper.RepoMapper
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.PlatformUtil
import com.gitfy.gitfyapi.util.vo.RepoDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


@Service
@CacheConfig(cacheNames = ["cache"])
class RepoService {

    @Autowired
    private lateinit var repoMapper: RepoMapper

    @Autowired
    private lateinit var platformUtil: PlatformUtil

    @Cacheable(key = "#root.methodName")
    fun getAllRepos(): List<RepoDetail> {
        val repoList = repoMapper.getAllRepos()
        return getRepoDetailList(repoList)
    }

    @Cacheable(key = "#root.args[0]")
    fun getReposByPlatform(platform: String): List<RepoDetail> {
        val repoList = repoMapper.getReposByPlatform(platform)
        return getRepoDetailList(repoList)
    }

    @Cacheable(key = "#root.args[0]+':'+#root.args[1]")
    fun getReposByOwner(platform: String, owner: String): List<RepoDetail> {
        val repoList = repoMapper.getReposByOwner(platform, owner)
        return getRepoDetailList(repoList)
    }

    @CacheEvict(allEntries = true)
    //@Async("asyncServiceExecutor")
    fun addRepo(repo: Repo) {
        if (repoMapper.ifRepoExists(repo) != 0) return
        if (platformUtil.addToRedis(repo)) repoMapper.addRepo(repo)
    }

    @CacheEvict(allEntries = true)
    fun removeRepo(repo: Repo) = repoMapper.removeRepo(repo)


    private fun getRepoDetailList(repoList: List<Repo>): List<RepoDetail> {
        val repoDetailList = mutableListOf<RepoDetail>()
        for (repo in repoList) repoDetailList.add(platformUtil.getFromRedis(repo))
        return repoDetailList
    }
}