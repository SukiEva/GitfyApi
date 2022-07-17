package com.gitfy.gitfyapi.controller

import com.gitfy.gitfyapi.factory.ResultFactory
import com.gitfy.gitfyapi.pojo.Result
import com.gitfy.gitfyapi.service.FollowService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class FollowController {


    private val logger = LoggerFactory.getLogger(FollowController::class.java)

    @Autowired
    private lateinit var followService: FollowService

    /**
     * 用户关注仓库接口
     *
     * @param uid 用户id
     * @return Result
     */
    @RequestMapping(
        value = ["/api/user/repos"],
        method = [RequestMethod.GET],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun getFollowByUid(
        @PathParam("uid") uid: String
    ): Result {
        val repos = followService.getFollowByUid(uid)
        logger.info("$uid————获取关注列表")
        return ResultFactory.buildSuccessResult("获取用户关注仓库成功", repos)
    }

}