package com.github.gitfy.gitfyapi.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

/**
 * 仓库信息 VO
 *
 * @author SukiEva
 */
@Data
public class RepoVO {
    @NonNull
    @JSONField(name = "name")
    private String platform;

    @NonNull
    @JSONField(name = "name")
    private String owner;

    @NonNull
    private String name;

    @JSONField(name = "full_name")
    private String fullName;

    private String description;

    @JSONField(name = "html_url")
    private String htmlUrl;

    private String homepage;

    private String readme;

    @JSONField(name = "updated_at")
    private Date updatedAt;

    private List<ReleaseVO> releases;
}
