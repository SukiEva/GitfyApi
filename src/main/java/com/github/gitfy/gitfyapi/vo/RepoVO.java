package com.github.gitfy.gitfyapi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RepoVO {
    private String platform;
    private String owner;
    private String name;
    private String fullName;
    private String description;
    private String htmlUrl;
    private String homepage;
    private String readme;
    private Date updatedAt;
    private List<ReleaseVO> releases;
}
