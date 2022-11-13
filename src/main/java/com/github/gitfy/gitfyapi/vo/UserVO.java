package com.github.gitfy.gitfyapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户VO
 *
 * @author Suki
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
