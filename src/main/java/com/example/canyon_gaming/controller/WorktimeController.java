package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.service.IWorktimeService;
import com.example.canyon_gaming.service.impl.dto.WorkTimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-21
 */
@RestController
@RequestMapping("/worktime")
public class WorktimeController {

    @Autowired
    IWorktimeService worktimeService;

    //添加排班时间
    @GetMapping("/add")
    public Result addTime(Date startTime, Date stopTime){
        return Result.success(worktimeService.addTime(startTime,stopTime));
    }

    //删除排班时间
    @GetMapping("/delete")
    public Result deleTime(Integer id){
        return Result.success(worktimeService.delTime(id));
    }

    //主播申请排班
    @GetMapping("/apply")
    public Result apply(Integer tid,Integer uid){
        return Result.success(worktimeService.applyTime(tid,uid));
    }

    //批准申请
    @GetMapping("/approve")
    public Result approve(Integer id){
        return Result.success(worktimeService.approveTime(id));
    }

    //拒绝申请
    @GetMapping("/refuse")
    public  Result refuse(Integer id){
        return Result.success(worktimeService.refuseTime(id));
    }

    //排班展示
    @GetMapping("/showTime")
    public Result showTime(){
        return Result.success(worktimeService.showTime());
    }

    //主播个人排班展示
    @GetMapping("/showMyTime")
    public Result showMyTime(Integer uid){
        return Result.success(worktimeService.showMyTime(uid));
    }

    //申请取消排班
    @GetMapping("/applyReTime")
    public Result applyReTime(Integer id){
        return Result.success(worktimeService.applyReTime(id));
    }

    //申请列表展示
    @GetMapping("/showApply")
    public Result showApply(){
        return Result.success(worktimeService.showApply());
    }

    //申请取消排班列表展示
    @GetMapping("/showReApply")
    public Result showReApply() {
        return Result.success(worktimeService.showReApply());
    }

    //批准取消排班
    @GetMapping("/approveReApply")
    public Result approveReApply(Integer id){
        return Result.success(worktimeService.approveReApply(id));
    }

    //拒绝取消排班
    @GetMapping("/refuseReApply")
    public Result refuseReApply(Integer id){
        return Result.success(worktimeService.refuseReApply(id));
    }

}
