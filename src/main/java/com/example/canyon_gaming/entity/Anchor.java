package com.example.canyon_gaming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    public Anchor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public Double getVirtualUrrency() {
        return virtualUrrency;
    }

    public void setVirtualUrrency(Double virtualUrrency) {
        this.virtualUrrency = virtualUrrency;
    }
    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    public Integer getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(Integer liveNum) {
        this.liveNum = liveNum;
    }

    @Override
    public String toString() {
        return "Anchor{" +
            "id=" + id +
            ", phone=" + phone +
            ", username=" + username +
            ", email=" + email +
            ", avatarUrl=" + avatarUrl +
            ", password=" + password +
            ", roomId=" + roomId +
            ", virtualUrrency=" + virtualUrrency +
            ", popularity=" + popularity +
            ", liveNum=" + liveNum +
                ",fans=" + fans +
                ",uid=" + uid +
        "}";
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getUid(){
        return uid;
    }

    public Integer getFans(){
        return fans;
    }
}
