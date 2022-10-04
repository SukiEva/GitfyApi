package com.github.gitfy.gitfyapi.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RepoVO {
    @NonNull
    private String platform;

    @NonNull
    private String owner;

    @NonNull
    private String name;

    private String fullName;
    private String description;
    private String htmlUrl;
    private String homepage;
    private String readme;
    private Date updatedAt;
    private List<ReleaseVO> releases;
}
