package com.gitfy.gitfyapi.mapper

import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.User
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Mapper
@Repository
interface UserMapper {

    fun generateUser(user: User)
    fun findUserByUid(uid: String): User?
    fun followRepo(uid: String, repo: Repo)
    fun unFollowRepo(uid: String, repo: Repo)
    fun bindTelegram(uid: String, tg: String)

}