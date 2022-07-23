package com.gitfy.gitfyapi.task

import com.gitfy.gitfyapi.mapper.RepoMapper
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.util.PlatformUtil
import com.gitfy.gitfyapi.util.SplitListUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

//@Component
//class UpdateTask {
//
//    private val logger = LoggerFactory.getLogger(UpdateTask::class.java)
//
//    @Autowired
//    private lateinit var repoMapper: RepoMapper
//
//    @Autowired
//    private lateinit var platformUtil: PlatformUtil
//
//    //private var repoList: List<Repo> = listOf()
//
//    private var perHourLists: List<List<Repo>> = listOf()
//
//    private var index = 0
//
//
//    @Scheduled(cron = "0 0 0 * * *")
//    fun clear() {
//        logger.info(LocalDateTime.now().toString() + "————重置仓库列表")
//        val repoList = repoMapper.getAllRepos()
//        perHourLists = SplitListUtil.split(repoList, 2000)
//        index = 0
//    }
//
//    @Scheduled(cron = "0 5 0 * * *", fixedRate = 3600000)
//    fun update() {
//        if (index >= perHourLists.size) {
//            return
//        }
//
//    }
//}