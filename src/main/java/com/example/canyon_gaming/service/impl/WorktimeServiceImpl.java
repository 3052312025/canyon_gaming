package com.example.canyon_gaming.service.impl;

import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Worktime;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.WorktimeMapper;
import com.example.canyon_gaming.service.IWorktimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canyon_gaming.service.impl.dto.WorkTimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-21
 */
@Service
public class WorktimeServiceImpl extends ServiceImpl<WorktimeMapper, Worktime> implements IWorktimeService {

    @Autowired
    WorktimeMapper worktimeMapper;

    @Autowired
    AnchorMapper anchorMapper;

    //添加排班时间
    @Override
    public String addTime(LocalDateTime startTime, LocalDateTime stopTime) {
        Worktime worktime = new Worktime();
        worktime.setStartTime(startTime);
        worktime.setStopTime(stopTime);
        worktimeMapper.insert(worktime);
        return "添加成功";
    }

    //删除排班时间
    @Override
    public String delTime(Integer id) {
        worktimeMapper.deleteById(id);
        return "删除成功";
    }


    //主播申请排班
    @Override
    public String applyTime(Integer tid, Integer uid) {
        //获取主播id
        Anchor anchor = anchorMapper.getByUid(uid);
        Worktime worktime = worktimeMapper.selectById(tid);
        if(worktime.getAid()==anchor.getId()){
            throw new ServiceException(Constants.CODE_600.getCode(), "您已提交申请，请耐心等待");
        }
        worktime.setAid(anchor.getId());
        worktime.setState(1);
        worktimeMapper.updateById(worktime);
        return "申请成功";
    }


    //管理员批准申请
    @Override
    public String approveTime(Integer id) {
        Worktime worktime = worktimeMapper.selectById(id);
        worktime.setState(0);
        worktimeMapper.updateById(worktime);
        return "已批准";
    }


    //管理员拒绝申请
    @Override
    public String refuseTime(Integer id) {
        Worktime worktime = worktimeMapper.selectById(id);
        worktime.setAid(-1);
        worktime.setState(-1);
        worktimeMapper.updateById(worktime);
        return "已拒绝";
    }


    //排班展示
    @Override
    public List<WorkTimeDto> showTime() {
        List<Worktime> worktimes = worktimeMapper.selectAll();
        List<WorkTimeDto> workTimeDtos = new ArrayList<>();
        for(int i = 0;i<worktimes.size();i++){
            String username = "";
            String state = "可申请";
            //根据aid查询用户名
            if(worktimes.get(i).getAid()!=-1){
                username = anchorMapper.selectById(worktimes.get(i).getAid()).getUsername();
            }
            if(worktimes.get(i).getState()==0){
                state = "已排班";
            }else if(worktimes.get(i).getState()==1){
                state = "申请中";
            }else if(worktimes.get(i).getState()==2){
                state = "取消申请中";
            }
            workTimeDtos.add(new WorkTimeDto(worktimes.get(i).getId(),worktimes.get(i).getStartTime(),worktimes.get(i).getStopTime(),username,state));
        }
        return workTimeDtos;
    }


    //主播个人排班展示
    @Override
    public List<WorkTimeDto> showMyTime(Integer uid) {
        List<Worktime> worktimes = worktimeMapper.selectByAid(anchorMapper.getByUid(uid).getId());
        List<WorkTimeDto> workTimeDtos = new ArrayList<>();
        for(int i = 0;i<worktimes.size();i++){
            String state = "";
            if(worktimes.get(i).getState()==0){
                state = "已排班";
            }else if(worktimes.get(i).getState()==2){
                state = "取消申请中";
            }
            workTimeDtos.add(new WorkTimeDto(worktimes.get(i).getId(),worktimes.get(i).getStartTime(),worktimes.get(i).getStopTime(),anchorMapper.getByUid(uid).getUsername(),state));
        }
        return workTimeDtos;
    }


    //申请取消排班
    @Override
    public String applyReTime(Integer id) {
        Worktime worktime = worktimeMapper.selectById(id);
        if(worktime.getState()==2){
            throw new ServiceException(Constants.CODE_600.getCode(), "您已提交申请，请耐心等待");
        }
        worktime.setState(2);
        worktimeMapper.updateById(worktime);
        return "申请成功";
    }


    //申请排班列表展示
    @Override
    public List<WorkTimeDto> showApply() {
        List<Worktime> worktimes = worktimeMapper.selectByState(1);
        List<WorkTimeDto> workTimeDtos = new ArrayList<>();
        for(int i = 0;i<worktimes.size();i++){
            String username = anchorMapper.selectById(worktimes.get(i).getAid()).getUsername();
            workTimeDtos.add(new WorkTimeDto(worktimes.get(i).getId(),worktimes.get(i).getStartTime(),worktimes.get(i).getStopTime(),username,"申请排班"));
        }
        return workTimeDtos;
    }


    //申请取消排班列表展示
    @Override
    public List<WorkTimeDto> showReApply() {
        List<Worktime> worktimes = worktimeMapper.selectByState(2);
        List<WorkTimeDto> workTimeDtos = new ArrayList<>();
        for(int i = 0;i<worktimes.size();i++){
            String username = anchorMapper.selectById(worktimes.get(i).getAid()).getUsername();
            workTimeDtos.add(new WorkTimeDto(worktimes.get(i).getId(),worktimes.get(i).getStartTime(),worktimes.get(i).getStopTime(),username,"申请取消排班"));
        }
        return workTimeDtos;
    }


    //批准取消排班
    @Override
    public String approveReApply(Integer id) {
        Worktime worktime = worktimeMapper.selectById(id);
        worktime.setAid(-1);
        worktime.setState(-1);
        worktimeMapper.updateById(worktime);
        return "已批准";
    }


    //拒绝取消排班
    @Override
    public String refuseReApply(Integer id) {
        Worktime worktime = worktimeMapper.selectById(id);
        worktime.setState(0);
        worktimeMapper.updateById(worktime);
        return "已拒绝";
    }


}