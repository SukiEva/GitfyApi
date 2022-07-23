package com.gitfy.gitfyapi.util

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * Redis 工具类
 */
@Component
class RedisUtil {

    private val logger = LoggerFactory.getLogger(RedisUtil::class.java)

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    /**
     * 读取数据
     *
     * @param key
     * @return Any?
     */
    fun get(key: String): Any? {
        if (key == "") return null
        try {
            return redisTemplate.opsForValue().get(key)
        } catch (e: Exception) {
            logger.error("Redis————读取错误：\n $e")
        }
        return null
    }

    /**
     * 写入数据
     *
     * @param key
     * @param value
     * @param expireTime
     * @return Boolean
     */
    fun set(key: String, value: Any, expireTime: Long? = null): Boolean {
        if (key == "") return false
        try {
            redisTemplate.opsForValue().set(key, value)
            expireTime?.let {
                redisTemplate.expire(key, it, TimeUnit.DAYS)
            }
            return true
        } catch (e: Exception) {
            logger.error("Redis————写入错误：\n $e")
        }
        return false
    }
}