package com.github.gitfy.gitfyapi.infrastructure;

import com.github.gitfy.gitfyapi.vo.ResultVO;
import org.apache.http.HttpStatus;

public class ResultFactory {
    public static ResultVO success(Object data) {
        return buildResult(HttpStatus.SC_OK, "Success", data);
    }

    public static ResultVO fail(String message, Object data) {
        return buildResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, message, data);
    }

    public static ResultVO buildResult(Integer code, String message, Object data) {
        return new ResultVO(code, message, data);
    }
}
