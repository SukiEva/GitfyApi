package com.github.gitfy.gitfyapi.dao;

import com.github.gitfy.gitfyapi.vo.RepoVO;
import com.github.gitfy.gitfyapi.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {
    void addUser(UserVO user);

    void deleteUser(UserVO user);

    UserVO findUser(String telegram);

    void followRepo(String uid, RepoVO repo);

    void unFollowRepo(String uid, RepoVO repo);
}
