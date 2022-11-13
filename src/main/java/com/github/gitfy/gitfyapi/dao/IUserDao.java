package com.github.gitfy.gitfyapi.dao;

import com.github.gitfy.gitfyapi.vo.UserVO;
import org.springframework.stereotype.Repository;

/**
 * 用户 DAO
 *
 * @author Suki
 */
@Repository
public interface IUserDao {
    /**
     * 添加用户
     *
     * @param user UserVO
     */
    void addUser(UserVO user);

    /**
     * 删除用户
     *
     * @param uid uid
     */
    void deleteUser(String uid);

    /**
     * 查找用户 by telegram
     *
     * @param telegram tg
     * @return UserVO
     */
    UserVO findUserByTelegram(String telegram);

    /**
     * 查找用户 by uid
     *
     * @param uid uid
     * @return UserVO
     */
    UserVO findUserByUid(String uid);

    /**
     * 查找用户 by name
     *
     * @param name name
     * @return UserVO
     */
    UserVO findUserByName(String name);
}
