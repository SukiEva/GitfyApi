package com.gitfy.gitfyapi.service

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class FollowServiceTest {

    @Autowired
    private lateinit var followService: FollowService

    private val uid = "d11be1953315445a95c65119a0c8c0a4"

    @Test
    fun getFollowByUid() {
        val list = followService.getFollowByUid(uid)
        println(list)
    }

    @Test
    fun ifRepoFollowed() {
        val follow = followService.ifRepoFollowed(uid, "github", "SukiEva", "GitfyApi")
        println(follow)
    }


}