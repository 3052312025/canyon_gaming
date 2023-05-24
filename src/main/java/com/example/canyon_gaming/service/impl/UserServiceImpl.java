package com.example.canyon_gaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.Liveroom;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.LiveroomMapper;
import com.example.canyon_gaming.mapper.UserMapper;
import com.example.canyon_gaming.service.IUserService;
import com.example.canyon_gaming.service.impl.dto.UserDto;
import com.example.canyon_gaming.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Console;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sen
 * @since 2023-05-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnchorMapper anchorMapper;

    @Autowired
    LiveroomMapper liveroomMapper;

    //用户登录功能
    @Override
    public UserDto login(Map<String, Object> loginMap) {
        User user = getOne(new QueryWrapper<User>().eq("username", loginMap.get("username")));
        if (user != null) {
            if (user.getPassword().equals(loginMap.get("password"))) {
                UserDto userDto = new UserDto();
                //将user和userDto相同的属性赋值
                BeanUtils.copyProperties(user, userDto);
                //设置token
                String token = TokenUtils.genToken(String.valueOf(user.getId()), user.getPassword());
                userDto.setToken(token);
                return userDto;
            } else {
                throw new ServiceException(Constants.CODE_600.getCode(), "密码错误");
            }
        } else {
            throw new ServiceException(Constants.CODE_600.getCode(), "用户名不存在");
        }
    }

    //注册功能实现
    @Override
    public String register(User registerUser) {
        //用户名正则，3到16位（字母，数字，下划线，减号）
        Pattern pUsername = Pattern.compile("^[a-zA-Z0-9_-||\u4e00-\u9fa5]{3,16}$");
        Matcher pu = pUsername.matcher(registerUser.getUsername());
        //匹配密码格式,4-16位且不能含有中文
        Pattern pPassword = Pattern.compile("^[^\\u4e00-\\u9fa5]{3,16}$");
        Matcher pp = pPassword.matcher(registerUser.getPassword());
        //匹配电话号码的格式
        Pattern pPhone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(178))\\d{8}$");
        Matcher mp = pPhone.matcher(registerUser.getPhone());
        //匹配邮箱的格式
//        Pattern pEmail = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
//        Matcher me = pEmail.matcher(registerUser.getEmail());
        if (!pu.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "用户名格式有误，请输入3-16位(可以是字母，数字，下划线，减号,中文)的有效字符!");
        }
        if (!pp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "密码格式有误,请输入4-16位且不能含有中文的有效字符!");
        }
        if (!mp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的电话号码!");
        }
//        if (!me.matches()) {
//            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的邮箱!");
//        }
        //根据传入的参数,从数据库中查询一条记录
        User useN = getOne(new QueryWrapper<User>().eq("username", registerUser.getUsername()));
        User useP = getOne(new QueryWrapper<User>().eq("phone", registerUser.getPhone()));
//        User useE = getOne(new QueryWrapper<User>().eq("email", registerUser.getEmail()));
        if (useN != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该用户名已存在,请重新输入!");
        } else if (useP != null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该电话号码已存在,请重新输入!");
        }
//        else if (useE != null) {
//            throw new ServiceException(Constants.CODE_600.getCode(), "该邮箱已存在,请重新输入");
//        }
        save(registerUser);
        return "注册成功!";
    }


    // 根据用户id删除用户
    @Override
    public String deleteById(Integer id) {
        if (!removeById(id)) {
            throw new ServiceException(Constants.CODE_600.getCode(), "操作失败!");
        }
        return "删除成功!";
    }

    //用户修改个人信息
    @Override
    public String modify(User user) {
        User user1 = getById(user.getId());
        if (user1 == null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "操作失败!");
        }
        //用户名正则，3到16位（字母，数字，下划线，减号）
        Pattern pUsername = Pattern.compile("^[a-zA-Z0-9_-||\u4e00-\u9fa5]{3,16}$");
        Matcher pu = pUsername.matcher(user.getUsername());
        //匹配密码格式,4-16位且不能含有中文
//        Pattern pPassword = Pattern.compile("^[^\\u4e00-\\u9fa5]{3,16}$");
//        Matcher pp = pPassword.matcher(user.getPassword());
        //匹配邮箱的格式
        Pattern pEmail = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        Matcher me = pEmail.matcher(user.getEmail());
        if (!pu.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "用户名格式有误，请输入3-16位(可以是字母，数字，下划线，减号)的有效字符!");
        }
//        if (!pp.matches()) {
//            throw new ServiceException(Constants.CODE_600.getCode(), "密码格式有误,请输入4-16位且不能含有中文的有效字符!");
//        }
        if (!me.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的邮箱!");
        }
        User useN = getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        User useE = getOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        if (useN != null && !useN.getUsername().equals(user1.getUsername())) {
            throw new ServiceException(Constants.CODE_600.getCode(), "用户名已存在!");
        } else if (useE != null && !useE.getEmail().equals(user1.getEmail())) {
            throw new ServiceException(Constants.CODE_600.getCode(), "邮箱已存在!");
        }
        updateById(user);
        return "修改成功!";
    }

    //分页展示用户列表
    @Override
    public IPage<User> selectByPage(Integer currentPage, Integer pageSize) {
        QueryWrapper<User> pageWrapper = new QueryWrapper<>();
        //大于1的用户，默认1是管理员
        pageWrapper.eq("level", 1)
                .or().eq("level", 3)
                .orderByAsc("id");

        pageWrapper.select(User.class, userPage -> !userPage.getColumn().equals("password"));
        //第一个参数为查询第几页,第二个参数为每页多少条记录
        Page<User> page = new Page<>(currentPage, pageSize);
        IPage<User> userIPage = userMapper.selectPage(page, pageWrapper);
        //sk
        userIPage.setTotal(userIPage.getTotal());
        return userIPage;
    }

    //用户申请主播功能实现
    @Override
    public String apply(User applyUser) {
        String username = applyUser.getUsername();
        User user = getOne(new QueryWrapper<User>().eq("username", username));
        if (user.getLevel() == 3) {
            throw new ServiceException(Constants.CODE_600.getCode(), "您已提交申请，请耐心等待管理员审核！");
        }
        user.setLevel(3);
        updateById(user);
        return "提交成功";
    }

    //申请人员展示功能实现
    @Override
    public List<User> show() {
        List<User> list = userMapper.getUsersByLevel(3);
        return list;
    }

    //批准审核申请功能实现
    @Override
    public String approve(User applyUser) {
//        更新用户等级
        applyUser.setLevel(2);
        updateById(applyUser);

//        //删除user中的用户
//        removeById(applyUser);
        //提供直播间号码
        //生成随机数作为直播间号码
        Random r = new Random();
        int i = r.nextInt(100000);
        List<Liveroom> list = liveroomMapper.selectAll();
        while (i < 10000) {
            i = r.nextInt(100000);
            //遍历RoomID防止相同
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getRoomid().equals(String.valueOf(i))) {
                    i = 0;
                }
            }
        }
        Liveroom liveroom = new Liveroom();
        liveroom.setRoomid(String.valueOf(i));
        liveroom.setDegreeofeat(0);
        liveroom.setTheme("NULL");
        liveroom.setNumberofclicks(0);
        liveroomMapper.insert(liveroom);
        //注册进主播表
        Anchor anchor = new Anchor();
        anchor.setRoomId(String.valueOf(i));
        anchor.setPhone(applyUser.getPhone());
        anchor.setEmail(applyUser.getEmail());
        anchor.setAvatarUrl(applyUser.getAvatarUrl());
        anchor.setUsername(applyUser.getUsername());
        anchor.setPassword(applyUser.getPassword());
        anchor.setUid(applyUser.getId());
        anchorMapper.insert(anchor);
        return "已批准申请";
    }

    //不批准通过审核功能实现
    @Override
    public String refuse(User applyUser) {
        applyUser.setLevel(1);
        updateById(applyUser);
        return "已拒绝申请";
    }


}
