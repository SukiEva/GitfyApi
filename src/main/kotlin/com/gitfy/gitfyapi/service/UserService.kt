package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.mapper.UserMapper
import com.gitfy.gitfyapi.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    fun register(user: User) {
        userMapper.register(user)
    }

    fun login(userName: String, password: String): User? {
        return userMapper.login(userName, password)
    }

    fun findUserByName(userName: String): User? {
        return userMapper.findUserByName(userName)
    }

    fun findUserByUid(uid: String): User? {
        return userMapper.findUserByUid(uid)
    }

    fun followRepo(uid: String, platform: String, owner: String, repo: String) {
        return userMapper.followRepo(uid, platform, owner, repo)
    }

    fun unFollowRepo(uid: String, platform: String, owner: String, repo: String) {
        return userMapper.unFollowRepo(uid, platform, owner, repo)
    }

    fun changeUserInfo(userName: String, nickName: String) {
        return userMapper.changeUserInfo(userName, nickName)
    }

    fun changeUserPassword(userName: String, password: String) {
        return userMapper.changeUserPassword(userName, password)
    }
}