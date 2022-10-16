package com.github.gitfy.gitfyapi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String uid;

    private String telegram;

    private boolean isAdmin;
}
