package com.gitfy.gitfyapi.util.vo

enum class ResultCode(var code: Int) {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);
}