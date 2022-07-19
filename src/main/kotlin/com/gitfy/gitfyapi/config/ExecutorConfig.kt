package com.gitfy.gitfyapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor


@Configuration
@EnableAsync
class ExecutorConfig {
    @Bean
    fun asyncServiceExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        //配置核心线程数
        executor.corePoolSize = 5
        //配置最大线程数
        executor.maxPoolSize = 20
        //配置队列大小
        executor.queueCapacity = 500
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("thread-")
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        //执行初始化
        executor.initialize()
        return executor
    }
}