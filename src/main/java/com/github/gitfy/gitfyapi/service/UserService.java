package com.github.gitfy.gitfyapi.service;

import com.github.gitfy.gitfyapi.dao.IFollowDao;
import com.github.gitfy.gitfyapi.dao.IUserDao;
import com.github.gitfy.gitfyapi.util.StringUtil;
import com.github.gitfy.gitfyapi.vo.RepoVO;
import com.github.gitfy.gitfyapi.vo.UserVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务
 *
 * @author SukiEva
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IFollowDao followDao;

    public UserVO addUser(String name, String password) {
        try {
            String uuid = UUID.randomUUID().toString();
            UserVO user = new UserVO(uuid, name, password, null, false);
            userDao.addUser(user);
            log.info("Successfully add user: name = {}, uuid = {}", name, uuid);
            return user;
        } catch (DataAccessException exception) {
            log.error("Fail to add user: {}", exception.getMessage());
            return null;
        }
    }

    public boolean followRepo(String uid, String platform, String owner, String name) {
        RepoVO repo = new RepoVO(platform, owner, name);
        if (followDao.ifRepoFollowed(uid, repo)) {
            log.warn("{} has already followed repo {}", uid, StringUtil.buildRepo(repo));
            return false;
        }
        try {
            followDao.followRepo(uid, repo);
            log.info("{} follow repo {}", uid, StringUtil.buildRepo(repo));
            return true;
        } catch (DataAccessException exception) {
            log.error("Fail to follow repo: {}", exception.getMessage());
            return false;
        }
    }

    public boolean unFollowRepo(String uid, String platform, String owner, String name) {
        RepoVO repo = new RepoVO(platform, owner, name);
        try {
            followDao.unFollowRepo(uid, repo);
            log.info("{} unfollow repo {}", uid, StringUtil.buildRepo(repo));
            return true;
        } catch (DataAccessException exception) {
            log.error("Fail to unfollow repo: {}", exception.getMessage());
            return false;
        }
    }

    public List<RepoVO> getReposByUid(String uid) {
        try {
            List<RepoVO> repos = followDao.getFollowsByUid(uid);
            log.info("{} successfully get repos", uid);
            return repos;
        } catch (DataAccessException exception) {
            log.error("{} fail to get repos: {}", uid, exception.getMessage());
            return new ArrayList<>();
        }
    }
}
