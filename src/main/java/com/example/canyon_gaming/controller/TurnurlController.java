package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.service.ITurnurlService;
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
 * @since 2023-05-21
 */
@RestController
@RequestMapping("/turnurl")
public class TurnurlController {

    @Resource
    ITurnurlService turnurlService;

    //添加
    @GetMapping("/add")
    public Result add(String url){
        return Result.success(turnurlService.add(url));
    }

    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        return Result.success(turnurlService.delete(id));
    }

    //展示
    @GetMapping("/show")
    public Result show(){
        return Result.success(turnurlService.show());
    }

}
