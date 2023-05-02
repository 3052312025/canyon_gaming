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
 * @since 2023-05-05
 */
@TableName("liveroom")
public class Liveroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 直播id 
     */
    private String roomid;

    /**
     * 热度
     */
    private Integer degreeofeat;

    /**
     * 主题
     */
    private String theme;

    /**
     * 直播点击次数
     */
    private Integer numberofclicks;
    //直播间名称
    private String roomname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    public Integer getDegreeofeat() {
        return degreeofeat;
    }

    public void setDegreeofeat(Integer degreeofeat) {
        this.degreeofeat = degreeofeat;
    }
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    public Integer getNumberofclicks() {
        return numberofclicks;
    }

    public void setNumberofclicks(Integer numberofclicks) {
        this.numberofclicks = numberofclicks;
    }

    @Override
    public String toString() {
        return "Liveroom{" +
            "id=" + id +
            ", roomid=" + roomid +
            ", degreeofeat=" + degreeofeat +
            ", theme=" + theme +
            ", numberofclicks=" + numberofclicks +
        "}";
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
}
