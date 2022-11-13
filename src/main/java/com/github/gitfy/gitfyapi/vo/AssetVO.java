package com.github.gitfy.gitfyapi.vo;

import lombok.Data;

import java.util.Date;

/**
 * Asset 信息 VO
 *
 * @author SukiEva
 */
@Data
public class AssetVO {
    private String url;

    private String name;

    private Long size;

    private Long downloadCount;

    private Date createdAt;

    private Date updatedAt;

    private String browserDownloadUrl;
}
