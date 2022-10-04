package com.github.gitfy.gitfyapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String uid;
    private String telegram;
    private boolean isAdmin;
}
