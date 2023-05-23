package com.example.canyon_gaming.service.impl.dto;

import com.example.canyon_gaming.entity.Theme;
import lombok.Data;

import java.util.List;
@Data
public class OpenLiveDto {
    private List<Theme> themes;
    private String imgurl;
    private String roomid;

    public OpenLiveDto(List<Theme> themes, String imgurl, String roomid) {
        this.themes = themes;
        this.imgurl = imgurl;
        this.roomid = roomid;
    }
}
