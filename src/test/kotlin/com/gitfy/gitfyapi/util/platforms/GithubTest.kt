package com.gitfy.gitfyapi.util.platforms

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class GithubTest {

    @Test
    fun test() {
        val repo = Github("SukiEva", "Homepage")
        val detail = repo.getRepoDetail()
        println(detail)
    }
}