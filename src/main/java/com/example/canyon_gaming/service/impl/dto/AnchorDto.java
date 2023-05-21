package com.example.canyon_gaming.service.impl.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AnchorDto {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String username;

    private String email;

    private String avatarUrl;

    /**
     * 直播id
     */
    private String roomId;

    /**
     * 虚拟币
     */
    private Double virtualUrrency;

    /**
     * 人气
     */
    private Integer popularity;

    /**
     * 直播次数
     */
    private Integer liveNum;

    //粉丝数
    private Integer fans;

    //user表id
    private Integer uid;

    private  String token;
}
