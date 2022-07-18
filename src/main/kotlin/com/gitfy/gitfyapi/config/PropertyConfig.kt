package com.gitfy.gitfyapi.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


/**
 * 读取 application.yml 属性
 */
@Component
@ConfigurationProperties(prefix = "platform")
object PropertyConfig {

    val github = Github

    @Component
    @ConfigurationProperties(prefix = "platform.gitub")
    object Github {
        lateinit var token: String
    }
}