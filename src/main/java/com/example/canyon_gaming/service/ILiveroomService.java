package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Liveroom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.entity.User;

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
    String start(User user);

}
