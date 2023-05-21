package com.example.canyon_gaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Follow;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.FollowMapper;
import com.example.canyon_gaming.mapper.UserMapper;
import com.example.canyon_gaming.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sen
 * @since 2023-05-21
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {
    @Resource
    FollowMapper followMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    AnchorMapper anchorMapper;

    //用户关注主播方法
    @Override
    public String follow(Integer uid, Integer aid) {
        QueryWrapper<Follow> followQueryWrapper = new QueryWrapper<>();
        followQueryWrapper.eq("uid", uid).eq("aid", aid);
        Follow follow = getOne(followQueryWrapper);
        if (follow != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "您已关注该主播");
        }
        if (userMapper.selectById(uid) == null || anchorMapper.selectById(aid) == null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "操作失败");
        }
        Follow follow1 = new Follow();
        follow1.setUid(uid);
        follow1.setAid(aid);
        Anchor anchor = anchorMapper.selectById(aid);
        anchor.setFans(anchor.getFans() + 1);
        anchor.setPopularity(anchor.getPopularity() + 10);
        anchorMapper.updateById(anchor);
        save(follow1);
        return "关注成功";
    }

    //用户关注列表
    @Override
    public IPage<Anchor> showAllFollowAnchor(Integer currentPage, Integer pageSize, Integer uid) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        List<Follow> follows = followMapper.selectList(queryWrapper);
        if (follows.size() == 0) {
            return null;
        }
        QueryWrapper<Anchor> anchorQueryWrapper = new QueryWrapper<>();
        for (int i = 0; i < follows.size(); i++) {
            anchorQueryWrapper.or().eq("id", follows.get(i).getAid());
        }
        //筛选出要返回的字段
        anchorQueryWrapper.select(Anchor.class, anchorPage -> anchorPage.getColumn().equals("id") ||
                anchorPage.getColumn().equals("username") || anchorPage.getColumn().equals("avatar_url"));
        //第一个参数为查询第几页,第二个参数为每页多少条记录
        Page<Anchor> page = new Page<>(currentPage, pageSize);
        IPage<Anchor> IPage = anchorMapper.selectPage(page, anchorQueryWrapper);
        return IPage;
    }

    //取消关注主播
    @Override
    public String cancelFollow(Integer uid, Integer aid) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("aid", aid);
        if (getOne(queryWrapper)==null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "操作失败");
        }
        Anchor anchor = anchorMapper.selectById(aid);
        anchor.setFans(anchor.getFans() - 1);
        anchor.setPopularity(anchor.getPopularity() - 10);
        anchorMapper.updateById(anchor);
        followMapper.delete(queryWrapper);
        return "已取消关注该主播";
    }
}
