package com.gitfy.gitfyapi.controller

import com.gitfy.gitfyapi.factory.ResultFactory
import com.gitfy.gitfyapi.pojo.Repo
import com.gitfy.gitfyapi.pojo.Result
import com.gitfy.gitfyapi.service.RepoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class RepoController {

    private val logger = LoggerFactory.getLogger(RepoController::class.java)

    @Autowired
    private lateinit var repoService: RepoService

    /**
     * 全部仓库信息接口
     *
     * @return Result
     */
    @RequestMapping(
        value = ["/api/repo/getAll"],
        method = [RequestMethod.GET],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun getAllRepos(): Result {
        val list = repoService.getAllRepos()
        logger.info("获取全部仓库信息")
        return ResultFactory.buildSuccessResult("获取全部仓库信息", list)
    }

    /**
     * 平台仓库信息接口
     *
     * @param platform 仓库平台
     * @return Reulst
     */
    @RequestMapping(
        value = ["/api/repo/getByPlatform"],
        method = [RequestMethod.GET],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun getReposByPlatform(
        @PathParam("platform") platform: String
    ): Result {
        val list = repoService.getReposByPlatform(platform)
        logger.info("获取${platform}仓库信息")
        return ResultFactory.buildSuccessResult("获取${platform}仓库信息", list)
    }

    /**
     * 作者仓库信息接口
     *
     * @param platform 仓库平台
     * @param owner 仓库作者
     * @return Result
     */
    @RequestMapping(
        value = ["/api/repo/getByOwner"],
        method = [RequestMethod.GET],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun getReposByOwner(
        @PathParam("platform") platform: String, @PathParam("owner") owner: String
    ): Result {
        val list = repoService.getReposByOwner(platform, owner)
        logger.info("获取${platform}/${owner}仓库信息")
        return ResultFactory.buildSuccessResult("获取${platform}/${owner}仓库信息", list)
    }

    /**
     * 添加仓库接口
     *
     * @param platform 仓库平台
     * @param owner 仓库作者
     * @param repo 仓库名称
     * @return Result
     */
    @RequestMapping(
        value = ["/api/repo/add"],
        method = [RequestMethod.POST],
        produces = ["application/json; charset = UTF-8"]
    )
    @ResponseBody
    fun addRepo(
        @PathParam("platform") platform: String,
        @PathParam("owner") owner: String,
        @PathParam("repo") repo: String
    ): Result {
        if (platform == "github" || platform == "gitlab") {
            repoService.addRepo(Repo(platform, owner, repo))
            logger.info("添加仓库————${platform}:${owner}:${repo}")
            return ResultFactory.buildSuccessResult("添加仓库")
        }
        return ResultFactory.buildFailResult("格式错误")
    }

}