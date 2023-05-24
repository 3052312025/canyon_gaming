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

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
        if (getOne(new QueryWrapper<Theme>().eq("theme", theme)) != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该主题已存在,勿重复添加!");
        } else {
            Theme theme1 = new Theme();
            theme1.setTheme(theme);
            if (save(theme1)) {
                return "添加成功";
            } else {
                throw new ServiceException(Constants.CODE_600.getCode(), "添加失败");
            }
        }

    }

    //删除主题
    @Override
    public String deleteTheme(Integer themeId) {
        if (themeMapper.deleteById(themeId) == 1) {
            return "删除成功";
        } else {
            throw new ServiceException(Constants.CODE_600.getCode(), "删除失败");
        }
    }

    //展示主题
    @Override
    public List<Theme> showTheme() {
        List<Theme> theme = themeMapper.selectList(null);
        return theme;
    }

    //展示主题和热度
    @Override
    public List<ThemeDto> show() {
        List<Theme> theme = themeMapper.selectList(null);
        List<ThemeDto> themeDtos = new ArrayList<>();
        for(int i=0;i<theme.size();i++){
            themeDtos.add(new ThemeDto(theme.get(i).getTheme(),theme.get(i).getTouch()));
        }
        return themeDtos;
    }



}
