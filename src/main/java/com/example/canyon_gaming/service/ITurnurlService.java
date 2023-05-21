package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Turnurl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-21
 */
public interface ITurnurlService extends IService<Turnurl> {

    //添加图片
    String add(String url);
    //删除
    String delete(Integer id);
}
