package com.example.canyon_gaming.service.impl;

import com.example.canyon_gaming.entity.Apply;
import com.example.canyon_gaming.mapper.ApplyMapper;
import com.example.canyon_gaming.service.IApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-13
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements IApplyService {

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public String apply(Integer uid, String theme) {
        Apply a = new Apply();
        a.setUid(uid);
        a.setTheme(theme);
        save(a);
        return null;
    }

    @Override
    public String reapply(Integer uid) {
        applyMapper.reapply(uid);
        return null;
    }
}
