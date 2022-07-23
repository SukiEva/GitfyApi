package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.pojo.Repo
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

    private val uid = "a582cfe1-a4ee-4543-a5c3-b8ea76e992c4"

    @Test
    fun getFollowByUid() {
        val list = followService.getFollowByUid(uid)
        println(list)
    }

    @Test
    fun ifRepoFollowed() {
        val follow = followService.ifRepoFollowed(
            uid, Repo(
                "github", "SukiEva", "GitfyApi"
            )
        )
        println(follow)
    }


}