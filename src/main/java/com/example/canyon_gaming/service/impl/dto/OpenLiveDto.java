package com.example.canyon_gaming.service.impl.dto;

import com.example.canyon_gaming.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenLiveDto {
    private List<Theme> themes;
    private String theme;
    private String imgurl;
    private String roomname;
    private Integer degreeofeat;
    private String code;
    private String serverAddress;

}
