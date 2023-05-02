package com.example.canyon_gaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Theme;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.ThemeMapper;
import com.example.canyon_gaming.service.IThemeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canyon_gaming.service.impl.dto.ThemeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-08
 */
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements IThemeService {

    @Autowired
    ThemeMapper themeMapper;

    //添加主题
    @Override
    public String addTheme(String theme) {
        Theme t = new Theme();
        if (theme==null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入主题!");
        }
        if(theme.equals(getOne(new QueryWrapper<Theme>().eq("theme",theme)))){
            throw new ServiceException(Constants.CODE_600.getCode(), "该主题已存在，请重新输入!");
        }
        t.setTheme(theme);
        themeMapper.insert(t);
        return "添加成功";
    }

    //删除主题
    @Override
    public String deleteTheme(Theme theme) {
        themeMapper.deleteById(theme.getId());
        return "删除成功";
    }

    //展示主题
    @Override
    public ThemeDto[] showTheme() {
        List<Theme> theme = themeMapper.showTheme();
        ThemeDto[] themeDtos = new ThemeDto[theme.size()];
        for(int i=0;i<theme.size();i++){
            themeDtos[i].setName(theme.get(i).getTheme());
            themeDtos[i].setValue(theme.get(i).getTouch());
        }
        return themeDtos;
    }

}
