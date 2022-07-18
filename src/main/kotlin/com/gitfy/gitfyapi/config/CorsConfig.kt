package com.gitfy.gitfyapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration.ALL
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 允许跨域访问
 */
@Configuration
class CorsConfig {
    @Bean
    fun addCorsConfig(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods(ALL)
                    .allowedHeaders(ALL).allowCredentials(true)
            }
        }
    }
}