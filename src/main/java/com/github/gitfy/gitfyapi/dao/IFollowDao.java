package com.github.gitfy.gitfyapi.dao;

import com.github.gitfy.gitfyapi.vo.RepoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关注 Dao
 *
 * @author SukiEva
 */
@Repository
public interface IFollowDao {
    /**
     * 关注仓库
     *
     * @param uid uid
     * @param repo RepoVO
     */
    void followRepo(String uid, RepoVO repo);

    /**
     * 取关仓库
     *
     * @param uid uid
     * @param repo RepoVO
     */
    void unFollowRepo(String uid, RepoVO repo);

    /**
     * 获取用户关注的仓库
     *
     * @param uid uid
     * @return List<RepoVO>
     */
    List<RepoVO> getFollowsByUid(String uid);

    /**
     * 判断用户是否关注某仓库
     *
     * @param uid uid
     * @param repo RepoVO
     * @return boolean
     */
    boolean ifRepoFollowed(String uid, RepoVO repo);
}
