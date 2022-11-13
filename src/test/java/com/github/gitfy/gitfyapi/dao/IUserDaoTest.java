package com.github.gitfy.gitfyapi.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.github.gitfy.gitfyapi.vo.UserVO;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

/**
 * 用户 DAO 测试
 *
 * @author SukiEva
 * @date 2022/11/13
 */
@Slf4j
@SpringBootTest
class IUserDaoTest {
    private static final String testUser = "testUser";

    private static final String testTelegram = "123456";

    private static final String uuid = UUID.randomUUID().toString();

    private static final UserVO userVO = new UserVO(uuid, testUser, "testPassword", testTelegram, false);

    @Autowired
    private IUserDao userDao;

    @Order(1)
    @Test
    void addUser() {
        boolean hasError = false;
        try {
            userDao.addUser(userVO);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(9)
    @Test
    void deleteUser() {
        boolean hasError = false;
        try {
            userDao.deleteUser(uuid);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(2)
    @Test
    void findUserByTelegram() {
        boolean hasError = false;
        try {
            userDao.findUserByTelegram(testTelegram);
            log.info("findUserByTelegram = {}", userVO);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(3)
    @Test
    void findUserByUid() {
        boolean hasError = false;
        try {
            userDao.findUserByUid(uuid);
            log.info("findUserByUid = {}", userVO);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }

    @Order(4)
    @Test
    void findUserByName() {
        boolean hasError = false;
        try {
            userDao.findUserByName(testUser);
            log.info("findUserByName = {}", userVO);
        } catch (DataAccessException e) {
            hasError = true;
            e.printStackTrace();
        }
        assertFalse(hasError);
    }
}