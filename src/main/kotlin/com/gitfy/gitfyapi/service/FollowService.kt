package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.mapper.FollowMapper
import com.gitfy.gitfyapi.pojo.Repo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FollowService {

    @Autowired
    lateinit var followMapper: FollowMapper

    fun getFollowByUid(uid: String): List<Repo> {
        return followMapper.getFollowByUid(uid)
    }

    fun ifRepoFollowed(uid: String, platform: String, owner: String, repo: String): Int {
        return followMapper.ifRepoFollowed(uid, platform, owner, repo)
    }
}