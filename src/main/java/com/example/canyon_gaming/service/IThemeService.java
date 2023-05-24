package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Theme;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canyon_gaming.service.impl.dto.ThemeDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2023-05-08
 */
public interface IThemeService extends IService<Theme> {
    //添加主题
    String addTheme(String theme);

    //删除主题
    String deleteTheme(Integer themeId);

    //显示主题
    List<Theme> showTheme();

    //展示主题和热度
    List<ThemeDto> show();


}
