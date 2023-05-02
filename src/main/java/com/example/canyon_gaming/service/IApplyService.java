package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-13
 */
public interface IApplyService extends IService<Apply> {
    //用户申请
    String apply(Integer uid,String theme);

    //拒绝申请
    String reapply(Integer uid);

}
