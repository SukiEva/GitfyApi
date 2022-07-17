package com.gitfy.gitfyapi.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil {

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
            e.printStackTrace()
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
                redisTemplate.expire(key, it, TimeUnit.SECONDS)
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}