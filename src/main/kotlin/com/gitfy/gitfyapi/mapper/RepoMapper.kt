package com.gitfy.gitfyapi.mapper

import com.gitfy.gitfyapi.pojo.Repo
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface RepoMapper {

    fun getAllRepos(): List<Repo>

    fun addRepo(repo: Repo)

    fun removeRepo(repo: Repo)

    fun ifRepoExists(repo: Repo): Int
}