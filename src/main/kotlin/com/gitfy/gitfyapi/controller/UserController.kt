package com.gitfy.gitfyapi.controller

import com.gitfy.gitfyapi.factory.ResultFactory
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
     * 用户登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return Result 用户信息
     */
    @RequestMapping(
        value = ["/api/login"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun login(
        @PathParam("username") username: String, @PathParam("password") password: String
    ): Result {
        val user = userService.login(username, password)
        if (user == null) {
            logger.info("$username————登录失败：密码错误")
            return ResultFactory.buildFailResult("账户密码错误")
        }
        logger.info("$username————登录成功")
        return ResultFactory.buildSuccessResult("登录成功", user)
    }

    /**
     * 用户注册接口
     *
     * @param username 用户名
     * @param nickname 昵称
     * @param password 密码
     * @return Result 用户信息
     */
    @RequestMapping(
        value = ["/api/register"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun register(
        @PathParam("username") username: String,
        @PathParam("nickname") nickname: String,
        @PathParam("password") password: String
    ): Result {
        if (userService.findUserByName(username) != null) {
            logger.info("$username————注册失败：用户名已存在")
            return ResultFactory.buildFailResult("用户名已存在")
        }
        val user = User(
            UUID.randomUUID().toString().replace("-", ""),
            username,
            nickname,
            password,
            "normal",
            ""
        )
        userService.register(user)
        logger.info("$username————注册成功")
        return ResultFactory.buildSuccessResult("注册成功", user)
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
        userService.followRepo(uid, platform, owner, repo)
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
        userService.unFollowRepo(uid, platform, owner, repo)
        logger.info("$uid————取消关注仓库：$platform/$owner/$repo")
        return ResultFactory.buildSuccessResult("已经取消关注")
    }

    /**
     * 用户修改信息接口
     *
     * @param userName 用户名
     * @param password 密码
     * @param nickName 新昵称
     * @return Resuly
     */
    @RequestMapping(
        value = ["/api/user/changeInfo"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun changeUserInfo(
        @PathParam("username") username: String,
        @PathParam("password") password: String,
        @PathParam("nickname") nickname: String
    ): Result {
        var user = userService.login(username, password)
        if (user == null) {
            logger.info("$username————修改信息错误：密码错误")
            return ResultFactory.buildFailResult("权限不足")
        }
        userService.changeUserInfo(username, nickname)
        user = userService.findUserByName(username)
        logger.info("$username————修改昵称成功：$nickname")
        return ResultFactory.buildSuccessResult("修改昵称成功", user)
    }

    /**
     * 用户修改密码接口
     *
     * @param userName 用户名
     * @param password 原密码
     * @param newPassword 新密码
     * @return Result
     */
    @RequestMapping(
        value = ["/api/user/changePassword"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun changeUserPassword(
        @PathParam("username") username: String,
        @PathParam("password") password: String,
        @PathParam("newPassword") newPassword: String
    ): Result {
        val user = userService.login(username, password)
        if (user == null) {
            logger.info("$username————修改密码失败：密码错误")
            return ResultFactory.buildFailResult("原密码错误")
        }
        userService.changeUserPassword(username, newPassword)
        return ResultFactory.buildSuccessResult("$username————修改密码成功")
    }

}