package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Msg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sen
 * @since 2023-05-27
 */
public interface IMsgService extends IService<Msg> {
    //保存评论
    Msg saveComment(Msg msg);
    //展示评论
    List<Msg> findAllMsgList();
    //删除评论
    String deleteMsg(Integer id, Integer pid);
}
