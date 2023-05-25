package com.example.canyon_gaming.service.impl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    //直播间状态
    private String state;
    //直播间图片
    private String imgurl;
    //总页数
    private int Pagenum;
    //房间号
    private String RoomId;
    //直播间url
    private String liveRoomUrl;
    //用户头像
    private String userUrl;


    public LiveroomDto(String username, Integer fans, Integer popularity, Integer degreeofeat, String theme, String roomname) {
        this.username = username;
        this.fans = fans;
        this.popularity = popularity;
        this.degreeofeat = degreeofeat;
        this.theme = theme;
        this.roomname = roomname;
    }

    public LiveroomDto(String username, Integer fans, Integer popularity, Integer degreeofeat, String theme, String roomname,String state,String imgurl,String roomId,String userUrl) {
        this.username = username;
        this.fans = fans;
        this.popularity = popularity;
        this.degreeofeat = degreeofeat;
        this.theme = theme;
        this.roomname = roomname;
        this.state=state;
        this.imgurl=imgurl;
        this.RoomId=roomId;
        this.userUrl=userUrl;
    }
}
