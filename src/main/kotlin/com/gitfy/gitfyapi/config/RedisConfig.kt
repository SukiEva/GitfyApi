package com.gitfy.gitfyapi.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * Redis 配置
 */
@Configuration
class RedisConfig {

    /**
     * 配置Jackson2JsonRedisSerializer序列化策略
     *
     * @return Jackson2JsonRedisSerializer<Any>
     */
    private fun serializer(): Jackson2JsonRedisSerializer<Any> {
        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        val objectMapper = ObjectMapper().apply {
            // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            //enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
            // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
            activateDefaultTyping(
                this.polymorphicTypeValidator,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
            )
        }
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)
        return jackson2JsonRedisSerializer
    }

    /**
     * Redis template 配置
     *
     * @param redisConnectionFactory redis连接工厂
     * @return RedisTemplate
     */
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        val stringRedisSerializer = StringRedisSerializer()
        val jackson2JsonRedisSerializer = serializer()
        redisTemplate.apply {
            // 配置连接工厂
            setConnectionFactory(redisConnectionFactory)
            // 配置 key 序列化方式
            keySerializer = stringRedisSerializer
            // 配置 value 序列化方式: 使用 Jackson2JsonRedisSerializer
            valueSerializer = jackson2JsonRedisSerializer
            // 配置 hash key 序列化方式
            hashKeySerializer = stringRedisSerializer
            // 配置 hash value 序列化方式
            hashValueSerializer = jackson2JsonRedisSerializer
            afterPropertiesSet()
        }
        return redisTemplate
    }

}