package com.github.gitfy.gitfyapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan(value = "com.github.gitfy.gitfyapi.dao")
@SpringBootApplication
public class GitfyApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitfyApiApplication.class, args);
    }
}
