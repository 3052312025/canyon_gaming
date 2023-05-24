package com.example.canyon_gaming.service.impl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class showAnchorDto {
    List<String> name;
    List<Integer> popularity;
    List<Integer> fans;
}
