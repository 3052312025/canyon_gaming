package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Liveroom;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.LiveroomMapper;
import com.example.canyon_gaming.service.IUserService;
import com.example.canyon_gaming.service.impl.AnchorServiceImpl;
import com.example.canyon_gaming.service.impl.LiveroomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sen
 * @since 2023-05-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;

    //登录访问
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> loginMap) {
        return Result.success(userService.login(loginMap));
    }

    //注册访问
    @PostMapping("/register")
    public Result register(@RequestBody User registerUser) {
        return Result.success(userService.register(registerUser));
    }

    //根据用户id删除用户
    @GetMapping("/deleteUserById")
    public Result deleteUserById(@RequestParam Integer id) {
        return Result.success(userService.deleteById(id));
    }

    //分页展示用户信息
    @GetMapping("/showAllUser")
    public Result showAllUser(@RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        return Result.success(userService.selectByPage(currentPage, pageSize));
    }
    @GetMapping("/show")
    public Result show() {
        return Result.success(userService.show());
    }
    //用户信息修改
    @PostMapping("/modifyUser")
    public Result modify(@RequestBody User user) {
        return Result.success(userService.modify(user));
    }

    //用户申请主播
    @PostMapping("/apply")
    public Result apply(@RequestBody User applyUser) {
        return Result.success(userService.apply(applyUser));
    }

    //管理员批准申请
    @PostMapping("/approve")
    public Result approve(@RequestBody User applyUser) {
        return Result.success(userService.approve(applyUser));
    }

    //管理员拒绝申请
    @PostMapping("/refuse")
    public Result refuse(@RequestBody User applyUser) {
        return Result.success(userService.refuse(applyUser));
    }

    //测试
    @GetMapping("test")
    public Result test() {
        return Result.success(userService.getById(1));
    }
}
