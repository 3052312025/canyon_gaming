package com.example.canyon_gaming.service.impl.dto;

import com.example.canyon_gaming.entity.Theme;
import lombok.Data;

import java.util.List;
@Data
public class OpenLiveDto {
    private List<Theme> themes;
    private String imgurl;
    private String roomname;

    public OpenLiveDto(List<Theme> themes, String imgurl, String roomname) {
        this.themes = themes;
        this.imgurl = imgurl;
        this.roomname = roomname;
    }
}
