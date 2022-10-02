package com.github.gitfy.gitfyapi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReleaseVO {
    private String url;
    private String tagName;
    private String name;
    private boolean prerelease;
    private Date createdAt;
    private Date publishedAt;
    private String body;
    private List<AssetVO> assets;
}
