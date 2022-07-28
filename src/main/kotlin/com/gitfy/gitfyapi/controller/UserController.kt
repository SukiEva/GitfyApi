package com.gitfy.gitfyapi.controller

import com.gitfy.gitfyapi.factory.ResultFactory
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.User
import com.gitfy.gitfyapi.service.FollowService
import com.gitfy.gitfyapi.service.UserService
import com.gitfy.gitfyapi.util.vo.Result
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping(
    value = ["/api/user"], produces = ["application/json; charset = UTF-8"]
)
@RestController
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var followService: FollowService


    /**
     * 生成用户接口
     *
     * @return 用户信息
     */
    @RequestMapping(
        value = ["/generate"], method = [RequestMethod.POST, RequestMethod.GET]
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
        value = ["/follow"], method = [RequestMethod.POST]
    )
    @ResponseBody
    fun follow(
        @RequestParam("uid") uid: String,
        @RequestParam("platform") platform: String,
        @RequestParam("owner") owner: String,
        @RequestParam("repo") repo: String
    ): Result {
        val r = Repo(platform, owner, repo)
        if (followService.ifRepoFollowed(uid, r) == 1) {
            logger.info("$uid————关注仓库重复：$platform/$owner/$repo")
            return ResultFactory.buildFailResult()
        }
        if (userService.followRepo(uid, r)) {
            logger.info("$uid————关注仓库：$platform/$owner/$repo")
            return ResultFactory.buildSuccessResult()
        }
        logger.info("$uid————关注仓库失败——仓库不存在：$platform/$owner/$repo")
        return ResultFactory.buildFailResult()
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
        value = ["/unFollow"], method = [RequestMethod.POST]
    )
    @ResponseBody
    fun unFollow(
        @RequestParam("uid") uid: String,
        @RequestParam("platform") platform: String,
        @RequestParam("owner") owner: String,
        @RequestParam("repo") repo: String
    ): Result {
        userService.unFollowRepo(uid, Repo(platform, owner, repo))
        logger.info("$uid————取关仓库：$platform/$owner/$repo")
        return ResultFactory.buildSuccessResult()
    }

    /**
     * 绑定Telegram接口
     *
     * @param uid 用户id
     * @param tg Telegram Id
     * @return Result
     */
    @RequestMapping(
        value = ["/bindTG"], method = [RequestMethod.POST]
    )
    @ResponseBody
    fun bindTelegram(
        @RequestParam("uid") uid: String, @RequestParam("tg") tg: String
    ): Result {
        userService.bindTelegram(uid, tg)
        logger.info("$uid————绑定 telegram 账号：$tg")
        return ResultFactory.buildSuccessResult()
    }

    /**
     * 全部关注接口
     *
     * @param uid 用户id
     * @return Result
     */
    @RequestMapping(
        value = ["/repos"], method = [RequestMethod.GET]
    )
    @ResponseBody
    fun getFollowByUid(
        @RequestParam("uid") uid: String
    ): Result {
        if (userService.findUserByUid(uid) == null) {
            logger.info("$uid————获取关注失败：用户不存在")
            return ResultFactory.buildFailResult()
        }
        val repos = followService.getFollowByUid(uid)
        logger.info("$uid————获取关注列表")
        return ResultFactory.buildSuccessResult(data = repos)
    }
}