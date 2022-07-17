package com.gitfy.gitfyapi.util

import com.gitfy.gitfyapi.pojo.Repo
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class RedisUtilTest {

    @Autowired
    private lateinit var redisUtil: RedisUtil

    @Test
    fun get() {
        val repo = redisUtil.get("github:SukiEva:GitfyApi")
        println(repo)
        val obj: Repo = repo as Repo
        println(obj)
    }

    @Test
    fun set() {
        val repo = Repo("github", "SukiEva", "GitfyApi")
        redisUtil.set("github:SukiEva:GitfyApi", repo)
    }
}