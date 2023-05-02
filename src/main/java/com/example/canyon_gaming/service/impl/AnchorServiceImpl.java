package com.example.canyon_gaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.UserMapper;
import com.example.canyon_gaming.service.IAnchorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-03
 */
@Service
public class AnchorServiceImpl extends ServiceImpl<AnchorMapper, Anchor> implements IAnchorService {

    @Autowired
    AnchorMapper anchorMapper;

    @Autowired
    UserMapper userMapper;

    //主播个人信息维护
    @Override
    public String modify(Anchor anchor) {
        //判断信息是否符合要求
        //用户名正则，3到16位（字母，数字，下划线，减号）
        Pattern pUsername = Pattern.compile("^[a-zA-Z0-9_-]{3,16}$");
        Matcher pu = pUsername.matcher(anchor.getUsername());
        //匹配密码格式,4-16位且不能含有中文
        Pattern pPassword = Pattern.compile("^[^\\u4e00-\\u9fa5]{3,16}$");
        Matcher pp = pPassword.matcher(anchor.getPassword());
        //匹配电话号码的格式
        Pattern pPhone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher mp = pPhone.matcher(anchor.getPhone());
        //匹配邮箱的格式
        Pattern pEmail = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        Matcher me = pEmail.matcher(anchor.getEmail());
        if (!pu.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "用户名格式有误，请输入3-16位(可以是字母，数字，下划线，减号)的有效字符!");
        }
        if (!pp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "密码格式有误,请输入4-16位且不能含有中文的有效字符!");
        }
        if (!mp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的电话号码!");
        }
        if (!me.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的邮箱!");
        }
        //根据传入的参数,从数据库中查询一条记录
        Anchor useN = anchorMapper.getName(anchor.getId(),anchor.getUsername());
        Anchor useP = anchorMapper.getPhone(anchor.getId(),anchor.getPhone());
        Anchor useE = anchorMapper.getEmail(anchor.getId(),anchor.getEmail());
        if (useN != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该用户名已存在,请重新输入");
        } else if (useP != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该电话号码已存在,请重新输入");
        } else if (useE != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该邮箱已存在,请重新输入");
        }
        updateById(anchor);

        return "修改成功";
    }

    //主播个人信息查看
    @Override
    public Anchor mine(User user) {
        return anchorMapper.getByUid(user.getId());
    }

    //查询6个最热门主播的热度值
    @Override
    public int[] getSixPopularity() {
        return anchorMapper.getSixPopularity();
    }

    //获取最热门的6个主播的粉丝数
    @Override
    public int[] getSixFans() {
        return anchorMapper.getSixFans();
    }

    //获取最热门的6个主播的用户名
    @Override
    public String[] getSixName() {
        return anchorMapper.getSixName();
    }


}
