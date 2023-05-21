package com.example.canyon_gaming.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Follow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sen
 * @since 2023-05-21
 */
public interface IFollowService extends IService<Follow> {
    //用户关注主播方法
    String follow(Integer uid, Integer aid);
    //用户关注列表
    IPage<Anchor> showAllFollowAnchor(Integer currentPage, Integer pageSize,Integer uid);
    //取消关注主播
    String cancelFollow(Integer uid, Integer aid);

}
