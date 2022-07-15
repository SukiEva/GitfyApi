package com.gitfy.gitfyapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GitfyApiApplication

fun main(args: Array<String>) {
    runApplication<GitfyApiApplication>(*args)
}
