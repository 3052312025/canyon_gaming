package com.example.canyon_gaming.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.service.impl.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sen
 * @since 2023-05-02
 */
public interface IUserService extends IService<User> {
    //登录接口
    UserDto login(Map<String,Object> loginMap);

    //注册接口
    String register(User registerUser);

    //根据用户id删除用户
    String deleteById(Integer  id);

    //用户修改个人信息接口
    String modify(User user);

    //分页展示所有用户列表
    IPage<User> selectByPage(Integer currentPage, Integer pageSize);

    //普通用户申请成为主播接口
    String apply(User applyUser);

    //申请主播人员展示
    List<User> show();

    //管理员批准审批主播申请接口
    String approve(User applyUser);

    //管理员不批准审批主播申请接口
    String refuse(User applyUser);
}
