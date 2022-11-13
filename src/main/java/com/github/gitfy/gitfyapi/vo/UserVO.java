package com.github.gitfy.gitfyapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户 VO
 *
 * @author SukiEva
 */
@Data
@AllArgsConstructor
public class UserVO {
    private String uid;

    private String name;

    private String password;

    private String telegram;

    private Boolean isAdmin;
}
