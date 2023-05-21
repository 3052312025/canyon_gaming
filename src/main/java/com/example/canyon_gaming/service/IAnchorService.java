package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Anchor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.service.impl.dto.AnchorDto;
import com.example.canyon_gaming.service.impl.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2023-05-03
 */
public interface IAnchorService extends IService<Anchor> {
    //主播登录信息
    AnchorDto login(Map<String, Object> loginMap);


    //主播个人信息维护
    String modify(Anchor anchor);

    //查看主播个人信息
    Anchor mine(String username);

    //获取最热门的6个主播的热度值
    List<Anchor> getSixPopularity();


}
