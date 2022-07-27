package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.mapper.RepoMapper
import com.gitfy.gitfyapi.mapper.UserMapper
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.User
import com.gitfy.gitfyapi.util.PlatformUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service


@Service
@CacheConfig(cacheNames = ["cache"])
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var repoMapper: RepoMapper

    @Autowired
    private lateinit var platformUtil: PlatformUtil

    fun generateUser(user: User) = userMapper.generateUser(user)

    fun findUserByUid(uid: String) = userMapper.findUserByUid(uid)

    @CacheEvict(key = "#root.args[0]")
    fun followRepo(uid: String, repo: Repo): Boolean {
        if (platformUtil.addToRedis(repo)) {
            userMapper.followRepo(uid, repo)
            // 仓库不存在，添加仓库
            if (repoMapper.ifRepoExists(repo) != 1) repoMapper.addRepo(repo)
            return true
        }
        return false
    }

    fun unFollowRepo(uid: String, repo: Repo) = userMapper.unFollowRepo(uid, repo)

    fun bindTelegram(uid: String, tg: String) = userMapper.bindTelegram(uid, tg)
}