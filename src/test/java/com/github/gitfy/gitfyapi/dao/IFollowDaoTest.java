package com.github.gitfy.gitfyapi.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.github.gitfy.gitfyapi.infrastructure.constant.Platform;
import com.github.gitfy.gitfyapi.vo.RepoVO;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.UUID;

/**
 * 关注 Dao 测试
 *
 * @author SukiEva
 * @date 2022/11/13
 */
@Slf4j
@SpringBootTest
class IFollowDaoTest {
    private static final String uuid = UUID.randomUUID().toString();

    private static final RepoVO repo = new RepoVO(Platform.GITHUB, "SukiEva", "Gitfy");

    @Autowired
    private IFollowDao followDao;

    @Order(1)
    @Test
    void followRepo() {
        boolean hasError = false;
        try {
            followDao.followRepo(uuid, repo);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(9)
    @Test
    void unFollowRepo() {
        boolean hasError = false;
        try {
            followDao.unFollowRepo(uuid, repo);
            val result = followDao.ifRepoFollowed(uuid, repo);
            log.info("ifRepoFollowed = {}", result);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(2)
    @Test
    void getFollowsByUid() {
        boolean hasError = false;
        try {
            val list = followDao.getFollowsByUid(uuid);
            log.info("getFollowsByUid = {}", list);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(3)
    @Test
    void ifRepoFollowed() {
        boolean hasError = false;
        try {
            val result = followDao.ifRepoFollowed(uuid, repo);
            log.info("ifRepoFollowed = {}", result);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }
}