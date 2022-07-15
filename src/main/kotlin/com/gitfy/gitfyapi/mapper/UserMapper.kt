package com.gitfy.gitfyapi.mapper

import com.gitfy.gitfyapi.pojo.User
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Mapper
@Repository
interface UserMapper {
    fun register(user: User)
    fun login(userName: String, password: String): User?
    fun findUserByName(userName: String): User?
    fun findUserByUid(uid: String): User?
    fun followRepo(uid: String, platform: String, owner: String, repo: String)
    fun unFollowRepo(uid: String, platform: String, owner: String, repo: String)
    fun changeUserInfo(userName: String, nickName: String)
    fun changeUserPassword(userName: String, password: String)
}