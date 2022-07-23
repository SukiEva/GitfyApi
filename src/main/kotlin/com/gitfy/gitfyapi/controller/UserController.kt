package com.gitfy.gitfyapi.controller

import com.gitfy.gitfyapi.factory.ResultFactory
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.Result
import com.gitfy.gitfyapi.pojo.User
import com.gitfy.gitfyapi.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.websocket.server.PathParam

@RestController
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userService: UserService

    /**
     * 生成用户接口
     *
     * @return 用户信息
     */
    @RequestMapping(
        value = ["/api/user/generate"],
        method = [RequestMethod.POST, RequestMethod.GET],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun generateUser(): Result {
        val user = User(UUID.randomUUID().toString(), "", false)
        userService.generateUser(user)
        logger.info("生成新用户————${user.uid}")
        return ResultFactory.buildSuccessResult("生成成功", user)
    }

    /**
     * 关注仓库接口
     *
     * @param uid 用户id
     * @param platform 仓库平台
     * @param owner 仓库拥有者
     * @param repo 仓库名称
     * @return Result
     */
    @RequestMapping(
        value = ["/api/user/follow"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun follow(
        @PathParam("uid") uid: String,
        @PathParam("platform") platform: String,
        @PathParam("owner") owner: String,
        @PathParam("repo") repo: String
    ): Result {
        userService.followRepo(uid, Repo(platform, owner, repo))
        logger.info("$uid————关注仓库：$platform/$owner/$repo")
        return ResultFactory.buildSuccessResult("关注成功")
    }

    /**
     * 取关仓库接口
     *
     * @param uid 用户id
     * @param platform 仓库平台
     * @param owner 仓库拥有者
     * @param repo 仓库名称
     * @return Result
     */
    @RequestMapping(
        value = ["/api/user/unFollow"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun unFollow(
        @PathParam("uid") uid: String,
        @PathParam("platform") platform: String,
        @PathParam("owner") owner: String,
        @PathParam("repo") repo: String
    ): Result {
        userService.unFollowRepo(uid, Repo(platform, owner, repo))
        logger.info("$uid————取关仓库：$platform/$owner/$repo")
        return ResultFactory.buildSuccessResult("已经取消关注")
    }

    /**
     * 绑定Telegram接口
     *
     * @param uid 用户id
     * @param tg Telegram Id
     * @return Result
     */
    @RequestMapping(
        value = ["/api/user/bindTG"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun bindTelegram(
        @PathParam("uid") uid: String, @PathParam("tg") tg: String
    ): Result {
        userService.bindTelegram(uid, tg)
        logger.info("$uid————绑定 telegram 账号：$tg")
        return ResultFactory.buildSuccessResult("绑定成功")
    }
}