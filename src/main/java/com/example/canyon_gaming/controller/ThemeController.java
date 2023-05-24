package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.Theme;
import com.example.canyon_gaming.service.IThemeService;
import com.example.canyon_gaming.service.impl.dto.ThemeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-08
 */
@RestController
@RequestMapping("/theme")
public class ThemeController {
    @Resource
    IThemeService themeService;

    //管理员添加主题
    @GetMapping("/addTheme")
    public Result addTheme(@RequestParam(value = "theme") String theme) {
        return Result.success(themeService.addTheme(theme));
    }

    //管理员删除主题
    @GetMapping("/deleteTheme")
    public Result deleteTheme(@RequestParam(value = "id") Integer themeId) {
        return Result.success(themeService.deleteTheme(themeId));
    }

    //显示主题
    @GetMapping("/showTheme")
    public List<Theme> showTheme() {
        return themeService.showTheme();
    }

    @GetMapping("/test")
    public String test(@RequestParam Integer id) {
        if (id == 1) {
            return "sss";
        } else {
            return "sddfdsf";
        }
    }

    //主题与热度
    @GetMapping("/getTheme")
    public Result get(){
        return Result.success(themeService.show());
    }


}
