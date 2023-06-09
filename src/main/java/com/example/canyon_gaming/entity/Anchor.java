package com.example.canyon_gaming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2023-05-03
 */
@TableName("anchor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Anchor implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 密码
     */
    private String password;

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

}
