package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.service.IAnchorService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-03
 */
@RestController
@RequestMapping("/anchor")
public class AnchorController {
    @Resource
    IAnchorService anchorService;

    //主播登录
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> loginMap) {
        return Result.success(anchorService.login(loginMap));
    }


    //根据用户名获取博主信息
    @GetMapping("/getAnchorByName")
    public Result mine(@RequestParam(value = "username") String username) {
        return Result.success(anchorService.mine(username));
    }

    //主播个人信息维护
    @PostMapping("/modifyAnchor")
    public Result modifyAnchor(@RequestBody Anchor anchor) {
        return Result.success(anchorService.modify(anchor));
    }

    //查询6个热门主播的热度值
    @GetMapping("/getSixPopularity")
    public Result getSixPopularity() {
        return Result.success(anchorService.getSixPopularity());
    }


    //test
    @GetMapping("/test")
    public Result test(@RequestParam Integer id) {
        return Result.success(anchorService.getById(id));
    }
}
