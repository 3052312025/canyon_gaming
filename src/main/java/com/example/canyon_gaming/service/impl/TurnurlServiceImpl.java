package com.example.canyon_gaming.service.impl;

import com.example.canyon_gaming.entity.Turnurl;
import com.example.canyon_gaming.mapper.TurnurlMapper;
import com.example.canyon_gaming.service.ITurnurlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-21
 */
@Service
public class TurnurlServiceImpl extends ServiceImpl<TurnurlMapper, Turnurl> implements ITurnurlService {

    @Resource
    TurnurlMapper turnurlMapper;

    @Override
    public String add(String url) {
        Turnurl turnurl = new Turnurl();
        turnurl.setUrl(url);
        turnurlMapper.insert(turnurl);
        return "添加成功";
    }

    @Override
    public String delete(Integer id) {
        turnurlMapper.deleteById(id);
        return "删除成功";
    }

    @Override
    public List<Turnurl> show() {
        return turnurlMapper.selectList(null);
    }
}
