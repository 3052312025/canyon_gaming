package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.service.IFollowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sen
 * @since 2023-05-21
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    IFollowService followService;

    //关注主播
    @GetMapping("/followAnchor")
    public Result follow(@RequestParam Integer uid, @RequestParam Integer aid) {
        return Result.success(followService.follow(uid, aid));
    }

    //分页展示用户关注列表
    @GetMapping("/showAllFollowAnchor")
    public Result showFollowAnchor(@RequestParam Integer currentPage, @RequestParam Integer pageSize, @RequestParam Integer uid) {
        return Result.success(followService.showAllFollowAnchor(currentPage, pageSize, uid));
    }

    //取消关注主播
    @GetMapping("/cancelFollowAnchor")
    public Result cancelFollowAnchor(@RequestParam Integer uid, @RequestParam Integer aid) {
        return Result.success(followService.cancelFollow(uid, aid));
    }

    //test
    @GetMapping("/test")
    public Result test() {
        return Result.success("hhhh");
    }
}
