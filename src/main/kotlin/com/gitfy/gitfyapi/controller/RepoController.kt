package com.gitfy.gitfyapi.controller

import com.gitfy.gitfyapi.factory.ResultFactory
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.service.RepoService
import com.gitfy.gitfyapi.service.UserService
import com.gitfy.gitfyapi.util.vo.Result
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RequestMapping(
    value = ["/api/repo"], produces = ["application/json; charset = UTF-8"]
)
@RestController
class RepoController {

    private val logger = LoggerFactory.getLogger(RepoController::class.java)

    @Autowired
    private lateinit var repoService: RepoService

    @Autowired
    private lateinit var userService: UserService

    /**
     * 获取仓库接口
     *
     * @param uid 用户id
     * @return Result
     */
    @RequestMapping(
        value = ["/get"], method = [RequestMethod.GET]
    )
    @ResponseBody
    fun getRepos(
        @PathParam("uid") uid: String
    ): Result {
        val user = userService.findUserByUid(uid)
        if (user == null || !user.isAdmin) {
            logger.info("$uid————获取仓库失败——权限不足")
            return ResultFactory.buildFailResult("权限不足")
        }
        val list = repoService.getAllRepos()
        logger.info("$uid————获取仓库成功")
        return ResultFactory.buildSuccessResult("获取仓库成功", list)
    }


    /**
     * 添加仓库接口
     *
     * @param uid 用户id
     * @param platform 仓库平台
     * @param owner 仓库作者
     * @param repo 仓库名称
     * @return Result
     */
    @RequestMapping(
        value = ["/add"], method = [RequestMethod.POST]
    )
    @ResponseBody
    fun addRepo(
        @PathParam("uid") uid: String,
        @PathParam("platform") platform: String,
        @PathParam("owner") owner: String,
        @PathParam("repo") repo: String
    ): Result {
        val user = userService.findUserByUid(uid)
        if (user == null || !user.isAdmin) {
            logger.info("$uid————添加仓库失败——权限不足：${platform}:${owner}:${repo}")
            return ResultFactory.buildFailResult("权限不足")
        }
        if (platform == "github" || platform == "gitlab") {
            repoService.addRepo(Repo(platform, owner, repo))
            logger.info("$uid————添加仓库成功：${platform}:${owner}:${repo}")
            return ResultFactory.buildSuccessResult("添加仓库成功")
        }
        return ResultFactory.buildFailResult("格式错误")
    }

    /**
     * 删除仓库接口
     *
     * @param uid 用户id
     * @param platform 仓库平台
     * @param owner 仓库作者
     * @param repo 仓库名称
     * @return Result
     */
    @RequestMapping(
        value = ["/remove"], method = [RequestMethod.POST]
    )
    @ResponseBody
    fun removeRepo(
        @PathParam("uid") uid: String,
        @PathParam("platform") platform: String,
        @PathParam("owner") owner: String,
        @PathParam("repo") repo: String
    ): Result {
        val user = userService.findUserByUid(uid)
        if (user == null || !user.isAdmin) {
            logger.info("$uid————删除仓库失败——权限不足：${platform}:${owner}:${repo}")
            return ResultFactory.buildFailResult("权限不足")
        }
        repoService.removeRepo(Repo(platform, owner, repo))
        logger.info("$uid————删除仓库成功：${platform}:${owner}:${repo}")
        return ResultFactory.buildSuccessResult("删除成功")
    }

}