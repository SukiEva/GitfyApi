package com.gitfy.gitfyapi.factory

import com.gitfy.gitfyapi.util.vo.Result
import com.gitfy.gitfyapi.util.vo.ResultCode

object ResultFactory {
    fun buildSuccessResult(message: String = "Success", data: Any? = null): Result {
        return buildResult(ResultCode.SUCCESS, message, data)
    }

    fun buildFailResult(message: String = "Fail", data: Any? = null): Result {
        return buildResult(ResultCode.FAIL, message, data)
    }

    private fun buildResult(resultCode: ResultCode, message: String, data: Any?): Result {
        return buildResult(resultCode.code, message, data)
    }

    private fun buildResult(resultCode: Int, message: String, data: Any?): Result {
        return Result(resultCode, message, data)
    }
}