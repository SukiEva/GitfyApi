package com.github.gitfy.gitfyapi.controller;

import com.github.gitfy.gitfyapi.infrastructure.ResultFactory;
import com.github.gitfy.gitfyapi.service.UserService;
import com.github.gitfy.gitfyapi.vo.RepoVO;
import com.github.gitfy.gitfyapi.vo.ResultVO;
import com.github.gitfy.gitfyapi.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/user", produces = "application/json; charset = UTF-8")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param telegram telegram 账号
     * @return ResultVO
     */
    @RequestMapping(value = "/generate", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO addUser(@RequestParam("telegram") String telegram) {
        UserVO user = userService.addUser(telegram);
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
    @ResponseBody
    public ResultVO followRepo(@RequestParam("uid") String uid, @RequestParam("platform") String platform,
                               @RequestParam("owner") String owner, @RequestParam("name") String name) {
        if (userService.followRepo(uid, platform, owner, name)) {
            return ResultFactory.success(null);
        } else {
            return ResultFactory.fail("Already followed or fail", null);
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
    @ResponseBody
    public ResultVO unFollowRepo(@RequestParam("uid") String uid, @RequestParam("platform") String platform,
                                 @RequestParam("owner") String owner, @RequestParam("name") String name) {
        if (userService.unFollowRepo(uid, platform, owner, name)) {
            return ResultFactory.success(null);
        } else {
            return ResultFactory.fail("Fail", null);
        }
    }

    /**
     * 通过 uid 获取关注列表
     *
     * @param uid uid
     * @return ResultVO
     */
    @RequestMapping(value = "/repos", method = {RequestMethod.GET})
    @ResponseBody
    public ResultVO getReposByUid(@RequestParam("uid") String uid) {
        List<RepoVO> repos = userService.getReposByUid(uid);
        return ResultFactory.success(repos);
    }
}