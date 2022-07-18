package com.gitfy.gitfyapi.service

import com.gitfy.gitfyapi.pojo.Repo
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class RepoServiceTest {

    @Autowired
    private lateinit var repoService: RepoService

    private val repo = Repo("github", "Lsposed", "Lsposed")

    @Test
    fun addRepo() {
        repoService.addRepo(repo)
    }

    @Test
    fun getAllRepos() {
        val list = repoService.getAllRepos()
        list.forEach {
            println(it)
        }
    }

}