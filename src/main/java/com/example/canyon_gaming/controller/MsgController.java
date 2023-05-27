package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.Msg;
import com.example.canyon_gaming.service.IMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sen
 * @since 2023-05-27
 */
@RestController
@RequestMapping("/msg")
public class MsgController {
    @Resource
    IMsgService msgService;

    //添加评论信息
    @PostMapping("/saveComment")
    public Result saveComment(@RequestBody Msg msg) {
        return Result.success(msgService.saveComment(msg));
    }

    //获取所有评论信息
    @GetMapping("/findAllMsg")
    public Result findAllMsgList() {
        return Result.success(msgService.findAllMsgList());
    }

    //删除用户评论
    @GetMapping("/deleteMsg")
    public Result deleteMsg(@RequestParam(value = "id") Integer id, @RequestParam(value = "pid") Integer pid) {
        return Result.success(msgService.deleteMsg(id, pid));
    }
}
