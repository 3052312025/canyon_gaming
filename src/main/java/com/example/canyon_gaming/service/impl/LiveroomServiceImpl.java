package com.example.canyon_gaming.service.impl;

import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Liveroom;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.LiveroomMapper;
import com.example.canyon_gaming.service.ILiveroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-05
 */
@Service
public class LiveroomServiceImpl extends ServiceImpl<LiveroomMapper, Liveroom> implements ILiveroomService {

    @Autowired
    AnchorMapper anchorMapper;

    @Autowired
    LiveroomMapper liveroomMapper;

    @Override
    public String start(User user) {
        //获取主播
        Anchor anchor = anchorMapper.getByUid(user.getId());
        //获取直播间
        Liveroom liveroom = liveroomMapper.getByRoomID(anchor.getRoomId());
//        热度设置为100
        if (liveroom.getDegreeofeat()!=0){
            throw new ServiceException(Constants.CODE_600.getCode(), "您已提交申请，请耐心等待管理员审核！");
        }
//        选取主题
//        主播直播次数加一

        return null;
    }
}
