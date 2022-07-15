package com.gitfy.gitfyapi.service

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

    private val uid = "d11be1953315445a95c65119a0c8c0a4"

    @Test
    fun register() {
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val user = User(
            uuid, "测试", "昵称", "test", "normal", ""
        )
        userService.register(user)
    }

    @Test
    fun login() {
        val user = userService.login("测试", "test")
        println(user)
    }

    @Test
    fun findUserByName() {
        val user = userService.findUserByName("测试")
        println(user)
    }

    @Test
    fun findUserByUid() {
        val user = userService.findUserByUid(uid)
        println(user)
    }

    @Test
    fun followRepo() {
        userService.followRepo(uid, "github", "SukiEva", "GitfyApi")
    }

    @Test
    fun unFollowRepo() {
        userService.unFollowRepo(uid, "github", "SukiEva", "GitfyApi")
    }

    @Test
    fun changeUserInfo() {
        userService.changeUserInfo("测试", "test")
    }

    @Test
    fun changeUserPassword() {
        userService.changeUserPassword("测试", "mytest")
    }
}