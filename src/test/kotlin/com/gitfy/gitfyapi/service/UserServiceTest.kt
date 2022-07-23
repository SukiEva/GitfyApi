package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.User
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private lateinit var userService: UserService

    private val uid = "a582cfe1-a4ee-4543-a5c3-b8ea76e992c4"

    @Test
    fun generateUser() {
        val user = User(UUID.randomUUID().toString(), "", false)
        userService.generateUser(user)
    }

    @Test
    fun findUserByUid() {
        val user = userService.findUserByUid(uid)
        println(user)
        println(userService.findUserByUid("null"))
    }

    @Test
    fun followRepo() {
        userService.followRepo(uid, Repo("github", "SukiEva", "GitfyApi"))
    }

    @Test
    fun unFollowRepo() {
        userService.unFollowRepo(uid, Repo("github", "SukiEva", "GitfyApi"))
    }

    @Test
    fun bindTelegram() {
        userService.bindTelegram(uid, "123")
    }


}