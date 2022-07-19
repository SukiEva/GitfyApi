package com.gitfy.gitfyapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GitfyApiApplication

fun main(args: Array<String>) {
    runApplication<GitfyApiApplication>(*args)
}
