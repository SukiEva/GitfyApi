package com.gitfy.gitfyapi.util

import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.vo.Assets
import com.gitfy.gitfyapi.util.vo.Release
import com.gitfy.gitfyapi.util.vo.RepoDetail
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
        val obj: RepoDetail = repo as RepoDetail
        println(obj)
    }

    @Test
    fun set() {
        val repo = RepoDetail(
            Repo("github", "SukiEva", "GitfyApi"),
            listOf(Release("", "", "", "", false, "", listOf(Assets("", "")))),
            "",""
        )
        redisUtil.set("github:SukiEva:GitfyApi", repo)
    }
}