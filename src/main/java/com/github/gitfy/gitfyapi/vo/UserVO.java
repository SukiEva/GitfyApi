package com.github.gitfy.gitfyapi.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private String uid;
    private String telegram;
    private boolean isAdmin;
}
