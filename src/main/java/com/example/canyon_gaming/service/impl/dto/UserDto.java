package com.example.canyon_gaming.service.impl.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 虚拟币
     */
    private Double virtualCurrency;
    /**
     * 权限，0是管理员，1是普通用户,2是主播,3是申请中用户
     */
    private Integer level;

    // 存储token
    private String token;

}
