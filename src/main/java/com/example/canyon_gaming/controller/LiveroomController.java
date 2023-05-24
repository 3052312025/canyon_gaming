package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.service.ILiveroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/liveroom")
public class LiveroomController {

    @Resource
    ILiveroomService liveroomService;

    //开播方法
    @GetMapping("/open")
    public Result Open(Integer id, String roomname, String theme,String imgurl){
        return Result.success(liveroomService.start(id,roomname,theme,imgurl));
    }

    //下播方法
    @GetMapping("/over")
    public Result Over(Integer id){
        return Result.success(liveroomService.over(id));
    }

    //直播间点击方法
    @GetMapping("/touch")
    public Result touch(String roomid){
        return Result.success(liveroomService.touch(roomid));
    }

    //直播间展示方法
    @GetMapping("/show")
    public Result show(Integer currentPage ,Integer pageSize ,String theme){
        return Result.success(liveroomService.show(currentPage,pageSize,theme));
    }

    @GetMapping("/getOld")
    public Result getOld(Integer id){
        return Result.success(liveroomService.getOld(id));
    }

}
