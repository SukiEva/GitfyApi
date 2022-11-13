package com.github.gitfy.gitfyapi.infrastructure.factory;

import com.github.gitfy.gitfyapi.vo.ResultVO;
import org.apache.http.HttpStatus;

/**
 * Controller 返回值工厂
 */
public class ResultFactory {
    public static ResultVO success(String message, Object data) {
        return buildResult(HttpStatus.SC_OK, message, data);
    }

    public static ResultVO success(Object data) {
        return success("Success", data);
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO fail(String message, Object data) {
        return buildResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, message, data);
    }

    public static ResultVO fail(Object data) {
        return fail("Fail", data);
    }

    public static ResultVO fail() {
        return fail(null);
    }

    public static ResultVO buildResult(Integer code, String message, Object data) {
        return new ResultVO(code, message, data);
    }
}
