package com.github.gitfy.gitfyapi.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AssetVO {
    private String url;
    private String name;
    private long size;
    private long downloadCount;
    private Date createdAt;
    private Date updatedAt;
    private String browserDownloadUrl;
}
