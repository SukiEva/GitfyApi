package com.github.gitfy.gitfyapi.vo;

import com.alibaba.fastjson2.annotation.JSONField;
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

    @JSONField(name = "download_count")
    private Long downloadCount;

    @JSONField(name = "created_at")
    private Date createdAt;

    @JSONField(name = "updated_at")
    private Date updatedAt;

    @JSONField(name = "browser_download_url")
    private String browserDownloadUrl;
}
