package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.Theme;
import com.example.canyon_gaming.service.IThemeService;
import com.example.canyon_gaming.service.impl.dto.ThemeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-08
 */
@Controller
@RequestMapping("/theme")
public class ThemeController {

    @Resource
    IThemeService themeService;

    //管理员添加主题
    @GetMapping("/addTheme/{theme}")
    public Result addTheme(@PathVariable String theme){
        return Result.success(themeService.addTheme(theme));
    }

    //管理员删除主题
    @PostMapping("/deleteTheme")
    public Result deleteTheme(@RequestBody Theme theme){
        return Result.success(themeService.deleteTheme(theme));
    }

    //获取主题与热度
    @PostMapping("/showTheme")
    public ThemeDto[] showTheme(){
        return themeService.showTheme();
    }

}
