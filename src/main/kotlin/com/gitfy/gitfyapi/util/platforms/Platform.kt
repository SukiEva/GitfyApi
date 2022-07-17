package com.gitfy.gitfyapi.util.platforms

import org.slf4j.LoggerFactory

abstract class Platform {

    private val logger = LoggerFactory.getLogger(Platform::class.java)

    open fun updateRepo() {
        logger.info("开始更新所有仓库")
    }
}