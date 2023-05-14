package com.example.canyon_gaming.service.impl.dto;

import com.example.canyon_gaming.entity.Anchor;
import lombok.Data;


public class LiveroomDto {
    //用户名
    private String username;
    //粉丝数
    private Integer fans;
    //人气
    private Integer popularity;
    //直播间热度
    private Integer degreeofeat;
    //直播间主题
    private String theme;
    //直播间名称
    private String roomname;

    public LiveroomDto(String username, Integer fans, Integer popularity, Integer degreeofeat, String theme, String roomname) {
        this.username = username;
        this.fans = fans;
        this.popularity = popularity;
        this.degreeofeat = degreeofeat;
        this.theme = theme;
        this.roomname = roomname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
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

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
}
