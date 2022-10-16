package com.github.gitfy.gitfyapi.dao;

import com.github.gitfy.gitfyapi.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {
    void addUser(UserVO user);

    void deleteUser(UserVO user);

    UserVO findUser(String telegram);
}
