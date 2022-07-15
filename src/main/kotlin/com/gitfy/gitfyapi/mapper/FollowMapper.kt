package com.gitfy.gitfyapi.mapper

import com.gitfy.gitfyapi.pojo.Repo
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface FollowMapper {
    fun getFollowByUid(uid: String): List<Repo>
    fun ifRepoFollowed(uid: String, platform: String, owner: String, repo: String): Int
}