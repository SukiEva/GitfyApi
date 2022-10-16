package com.github.gitfy.gitfyapi.dao;

import com.github.gitfy.gitfyapi.vo.RepoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowDao {
    void followRepo(String uid, RepoVO repo);

    void unFollowRepo(String uid, RepoVO repo);

    List<RepoVO> getFollowsByUid(String uid);

    int ifRepoFollowed(String uid, RepoVO repo);
}
