package com.example.canyon_gaming.service.impl;


import cn.hutool.core.util.StrUtil;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Liveroom;
import com.example.canyon_gaming.entity.Theme;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.FollowMapper;
import com.example.canyon_gaming.mapper.LiveroomMapper;
import com.example.canyon_gaming.mapper.ThemeMapper;
import com.example.canyon_gaming.service.ILiveroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canyon_gaming.service.impl.dto.LiveroomDto;
import com.example.canyon_gaming.service.impl.dto.OpenLiveDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-05
 */
@Service
public class LiveroomServiceImpl extends ServiceImpl<LiveroomMapper, Liveroom> implements ILiveroomService {

    @Resource
    AnchorMapper anchorMapper;

    @Resource
    LiveroomMapper liveroomMapper;

    @Value("${live-room.open-live.server-address}")
    String serverAddress;

    @Value("${live-room.open-live.flv-url}")
    String flvUrl;
    @Resource
    FollowMapper followMapper;

    //开播方法
    @Override
    public String start(Integer uid, String roomname, String theme, String imgurl) {
        //获取主播
        Anchor anchor = anchorMapper.getByUid(uid);
        //获取直播间
        Liveroom liveroom = liveroomMapper.getByRoomID(anchor.getRoomId());
//        热度设置为100，选取主题加入
        if (liveroom.getDegreeofeat() != 0) {
            throw new ServiceException(Constants.CODE_600.getCode(), "您已开播！");
        }
        if (StrUtil.isBlank(theme)) {
            throw new ServiceException(Constants.CODE_400.getCode(), "请选择主题！");
        }
        liveroom.setTheme(theme);
        liveroom.setRoomname(roomname);
        liveroom.setDegreeofeat(100);
        if (imgurl != null) {
            liveroom.setImgurl(imgurl);
        }
        //        主播直播次数加一
        anchor.setLiveNum(anchor.getLiveNum() + 1);
        liveroomMapper.updateById(liveroom);
        return "成功开播！";
    }

    //下播方法
    @Override
    public String over(Integer uid) {
        //获取主播
        Anchor anchor = anchorMapper.getByUid(uid);
        //获取直播间
        Liveroom liveroom = liveroomMapper.getByRoomID(anchor.getRoomId());
        //结算虚拟币
        double urrency = anchor.getVirtualUrrency() + liveroom.getDegreeofeat() * 0.1 + liveroom.getNumberofclicks() * 0.1 + anchor.getLiveNum();
        System.out.println("*-*-*-*-*-*-*-*-*" + urrency);
        anchor.setVirtualUrrency(urrency);
        anchorMapper.updateById(anchor);
        //设置热度为0
        liveroom.setDegreeofeat(0);
        liveroomMapper.updateById(liveroom);
        return "直播已结束！";
    }

    //直播间点击方法
    @Override
    public LiveroomDto touch(String roomId,Integer uid) {
        //获取直播间
        Liveroom liveroom = liveroomMapper.getByRoomID(roomId);
        //获取主播
        Anchor anchor = anchorMapper.getByRoomId(roomId);
        liveroom.setNumberofclicks(liveroom.getNumberofclicks() + 1);
        liveroomMapper.updateById(liveroom);
        //修改主播人气
        anchor.setPopularity(anchor.getPopularity() + 10);
        anchorMapper.updateById(anchor);
        if (liveroom.getDegreeofeat() != 0) {
            //修改热度
            liveroom.setDegreeofeat(liveroom.getDegreeofeat() + 10);
            liveroomMapper.updateById(liveroom);
        }
        //生成直播间url
        String liveRoomUrl =flvUrl + "/" + "liveroom_" + anchor.getRoomId()+".flv";
        System.out.println(liveRoomUrl);
        //是否关注
        Boolean gz = false;
        List<Integer> list = followMapper.getByUid(uid);
        for(int i =0;i<list.size();i++){
            if(anchor.getId()==list.get(i)){
                gz = true;
            }
        }
        //返回封装数据
        LiveroomDto liveroomDto = new LiveroomDto(anchor.getUsername(), anchor.getFans(), anchor.getPopularity(), liveroom.getDegreeofeat(), liveroom.getTheme(), liveroom.getRoomname(), "", anchor.getAvatarUrl(), 0, liveroom.getRoomid(), liveRoomUrl,anchor.getAvatarUrl(),anchor.getId(),gz);
        return liveroomDto;
    }

    //首页展示
    @Override
    public List<LiveroomDto> show(Integer currentPage, Integer pageSize, String theme) {
        List<LiveroomDto> liveroomDtos = new ArrayList<>();
        //获取全部主播
        List<Anchor> anchors = anchorMapper.selectList(null);
        //获取总页数


        for (int i = 0; i < anchors.size(); i++) {
            //获取主播
            Anchor anchor = anchors.get(i);
            //获取主播对应的直播间
            Liveroom liveroom = liveroomMapper.getByRoomID(anchor.getRoomId());
            System.out.println(liveroom.getImgurl());
            //封装数据
            String state = "主播暂未开播";
            if (liveroom.getDegreeofeat() != 0) {
                state = "直播中";
            }
            if (theme.equals("全部")) {
                liveroomDtos.add(new LiveroomDto(anchor.getUsername(), anchor.getFans(), anchor.getPopularity(), liveroom.getDegreeofeat(), liveroom.getTheme(), liveroom.getRoomname(), state, liveroom.getImgurl(), anchor.getRoomId(),anchor.getAvatarUrl()));
            } else if (liveroom.getTheme().equals(theme)) {
                liveroomDtos.add(new LiveroomDto(anchor.getUsername(), anchor.getFans(), anchor.getPopularity(), liveroom.getDegreeofeat(), liveroom.getTheme(), liveroom.getRoomname(), state, liveroom.getImgurl(), anchor.getRoomId(),anchor.getAvatarUrl()));
            }
        }
        return getList(currentPage, pageSize, liveroomDtos);
    }


    //分页方法
    List<LiveroomDto> getList(Integer currentPage, Integer pageSize, List<LiveroomDto> list) {
        List<LiveroomDto> liveroomDtos = new ArrayList<>();
        int x = (currentPage - 1) * pageSize;
        int z = currentPage * pageSize;
        if (pageSize == 0) {
            return null;
        }
        if (z > list.size()) {
            z = list.size();
        } else if (z == 0) {
            z = 1;
        }
        for (int i = x; i < z; i++) {
            list.get(i).setPagenum(list.size());
            liveroomDtos.add(list.get(i));
        }
        return liveroomDtos;
    }


    @Resource
    ThemeMapper themeMapper;

    //原先信息返回
    @Override
    public OpenLiveDto getOld(Integer uid) {
        //获取主播对象
        Anchor anchor = anchorMapper.getByUid(uid);
        //获取直播间对象
        Liveroom liveroom = liveroomMapper.getByRoomID(anchor.getRoomId());
        //获取主题
        List<Theme> themes = themeMapper.selectList(null);
        //生成推流码
        String uuid = "liveroom_" + anchor.getRoomId();
        OpenLiveDto openLiveDto = new OpenLiveDto(themes, liveroom.getTheme(), liveroom.getImgurl(), liveroom.getRoomname(), liveroom.getDegreeofeat(), uuid, serverAddress);
        return openLiveDto;
    }
}
