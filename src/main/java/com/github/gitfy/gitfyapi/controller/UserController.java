package com.github.gitfy.gitfyapi.controller;

import com.github.gitfy.gitfyapi.infrastructure.factory.ResultFactory;
import com.github.gitfy.gitfyapi.service.UserService;
import com.github.gitfy.gitfyapi.vo.RepoVO;
import com.github.gitfy.gitfyapi.vo.ResultVO;
import com.github.gitfy.gitfyapi.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 API
 *
 * @author SukiEva
 */
@RestController
@ResponseBody
@RequestMapping(value = "/api/user", produces = "application/json; charset = UTF-8")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param name     用户名
     * @param password 用户密码
     * @return ResultVO
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResultVO addUser(@RequestParam("name") String name, @RequestParam("password") String password) {
        UserVO user = userService.addUser(name, password);
        return ResultFactory.success(user);
    }

    /**
     * 用户关注仓库
     *
     * @param uid      uid
     * @param platform 仓库平台
     * @param owner    仓库作者
     * @param name     仓库名称
     * @return ResultVO
     */
    @RequestMapping(value = "/follow", method = {RequestMethod.POST})
    public ResultVO followRepo(@RequestParam("uid") String uid, @RequestParam("platform") String platform,
                               @RequestParam("owner") String owner, @RequestParam("name") String name) {
        if (userService.followRepo(uid, platform, owner, name)) {
            return ResultFactory.success();
        } else {
            return ResultFactory.fail("Fail or already follow", null);
        }
    }

    /**
     * 用户取关仓库
     *
     * @param uid      uid
     * @param platform 仓库平台
     * @param owner    仓库作者
     * @param name     仓库名称
     * @return ResultVO
     */
    @RequestMapping(value = "/unfollow", method = {RequestMethod.POST})
    public ResultVO unFollowRepo(@RequestParam("uid") String uid, @RequestParam("platform") String platform,
                                 @RequestParam("owner") String owner, @RequestParam("name") String name) {
        if (userService.unFollowRepo(uid, platform, owner, name)) {
            return ResultFactory.success();
        } else {
            return ResultFactory.fail();
        }
    }

    /**
     * 通过 uid 获取关注列表
     *
     * @param uid uid
     * @return ResultVO
     */
    @RequestMapping(value = "/repos", method = {RequestMethod.GET})
    public ResultVO getReposByUid(@RequestParam("uid") String uid) {
        List<RepoVO> repos = userService.getReposByUid(uid);
        return ResultFactory.success(repos);
    }
}
