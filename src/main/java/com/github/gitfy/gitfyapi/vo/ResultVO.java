package com.github.gitfy.gitfyapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回结果 VO
 *
 * @author Suki
 */
@Data
@AllArgsConstructor
public class ResultVO {
    private Integer code;

    private String message;

    private Object data;
}
