package com.github.gitfy.gitfyapi.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Relese 信息 VO
 *
 * @author Suki
 */
@Data
public class ReleaseVO {
    private String url;

    private String tagName;

    private String name;

    private Boolean prerelease;

    private Date createdAt;

    private Date publishedAt;

    private String body;

    private List<AssetVO> assets;
}
