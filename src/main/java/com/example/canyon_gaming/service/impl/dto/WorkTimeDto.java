package com.example.canyon_gaming.service.impl.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class WorkTimeDto {
    //id
    private Integer id;
    //开始时间
    private String startTime;
    //结束时间
    private String stopTime;
    //用户名
    private String username;
    //状态
    private String state;

    public WorkTimeDto(Integer id, String startTime, String stopTime, String username, String state) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.username = username;
        this.state = state;
    }
}
