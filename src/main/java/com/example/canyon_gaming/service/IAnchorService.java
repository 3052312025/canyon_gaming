package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Anchor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-03
 */
public interface IAnchorService extends IService<Anchor> {

    //主播个人信息维护
    String modify(Anchor anchor);

    //查看主播个人信息
    Anchor mine(User user);

    //获取最热门的6个主播的热度值
    int[] getSixPopularity();

    //获取最热门的6个主播的粉丝数
    int[] getSixFans();

    //获取最热门的6个主播的用户名
    String[] getSixName();

}
