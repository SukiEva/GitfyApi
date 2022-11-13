package com.github.gitfy.gitfyapi.dao;

import com.github.gitfy.gitfyapi.vo.RepoVO;

import java.util.List;

public interface IRepoDao {
    List<RepoVO> getRepos();

    void addRepo();

    void deleteRepo();

    int ifRepoExists();
}
