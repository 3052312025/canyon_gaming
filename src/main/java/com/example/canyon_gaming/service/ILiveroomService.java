package com.example.canyon_gaming.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.canyon_gaming.entity.Liveroom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.service.impl.dto.LiveroomDto;
import com.example.canyon_gaming.service.impl.dto.OpenLiveDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-05
 */
public interface ILiveroomService extends IService<Liveroom> {

    //开播方法
    String start(Integer id, String roomname, String theme, String imgurl);

    //下播方法
    String over(Integer id);

    //直播间点击方法
    LiveroomDto touch(String roomId,Integer uid);

    //主页显示直播间
    List<LiveroomDto> show(Integer Page, Integer pageSize, String theme);

    //显示原来的信息
    OpenLiveDto getOld(Integer id);

}
