package com.gitfy.gitfyapi.task

import com.gitfy.gitfyapi.mapper.RepoMapper
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.PlatformUtil
import com.gitfy.gitfyapi.util.SplitListUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.concurrent.thread

@Component
class UpdateTask {

    private val logger = LoggerFactory.getLogger(UpdateTask::class.java)

    @Autowired
    private lateinit var repoMapper: RepoMapper

    @Autowired
    private lateinit var platformUtil: PlatformUtil

    private var repoList: List<Repo> = listOf()
    private var perHourLists: List<List<Repo>> = listOf()
    private var curList: List<Repo> = listOf()
    private var curIndex = 0

    private fun initData() {
        if (repoList.isEmpty() || curIndex >= perHourLists.size) {
            repoList = repoMapper.getAllRepos()
            perHourLists = SplitListUtil.split(repoList, 1000)
            curIndex = 0
        }
        curList = perHourLists[curIndex++]
    }

    @Async("asyncServiceExecutor")
    @Scheduled(cron = "0 5 * * * *")
    //@Scheduled(fixedDelay = 10000)
    fun update() {
        initData()
        val lists = SplitListUtil.split(curList, 50)
        for (list in lists) {
            thread {
                logger.info("更新仓库集：$list")
                for (repo in list) platformUtil.addToRedis(repo)
            }
        }
    }

}