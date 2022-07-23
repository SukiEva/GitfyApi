package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.mapper.UserMapper
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    fun generateUser(user: User) = userMapper.generateUser(user)

    fun findUserByUid(uid: String) = userMapper.findUserByUid(uid)

    fun followRepo(uid: String, repo: Repo) = userMapper.followRepo(uid, repo)

    fun unFollowRepo(uid: String, repo: Repo) = userMapper.unFollowRepo(uid, repo)

    fun bindTelegram(uid: String, tg: String) = userMapper.bindTelegram(uid, tg)
}