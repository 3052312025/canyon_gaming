package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Worktime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.service.impl.dto.WorkTimeDto;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-21
 */
public interface IWorktimeService extends IService<Worktime> {

    //添加排班
    String addTime(String startTime ,String stopTime);

    //主播申请排班时间
    String applyTime(Integer tid,Integer uid);

    //管理员批准申请
    String approveTime(Integer id);

    //管理员拒绝申请
    String refuseTime(Integer id);

    //删除排班时间
    String delTime(Integer id);

    //申请取消排班
    String applyReTime(Integer id);

    //*******************************************
    //排班展示
    List<WorkTimeDto> showTime();

    //主播个人排班展示
    List<WorkTimeDto> showMyTime(Integer uid);

    //申请列表展示
    List<WorkTimeDto> showApply();

    //申请取消排班列表展示
    List<WorkTimeDto> showReApply();

    //********************************************
    //批准取消排班
    String approveReApply(Integer id);

    //拒绝取消排班
    String refuseReApply(Integer id);
}
