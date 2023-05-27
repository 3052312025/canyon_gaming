package com.example.canyon_gaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Anchor;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.AnchorMapper;
import com.example.canyon_gaming.mapper.UserMapper;
import com.example.canyon_gaming.service.IAnchorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canyon_gaming.service.IUserService;
import com.example.canyon_gaming.service.impl.dto.AnchorDto;
import com.example.canyon_gaming.service.impl.dto.UserDto;
import com.example.canyon_gaming.service.impl.dto.showAnchorDto;
import com.example.canyon_gaming.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Element;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 服务实现类
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

    @Autowired
    IUserService iUserService;

    //主播登录
    @Override
    public AnchorDto login(Map<String, Object> loginMap) {
        Anchor anchor = getOne(new QueryWrapper<Anchor>().eq("username", loginMap.get("username")));
        if (anchor != null) {
            if (anchor.getPassword().equals(loginMap.get("password"))) {
                AnchorDto anchorDto = new AnchorDto();
                //将user和userDto相同的属性赋值
                BeanUtils.copyProperties(anchor, anchorDto);
                //设置token
                String token = TokenUtils.genToken(String.valueOf(anchor.getUid()), anchor.getPassword());
                anchorDto.setToken(token);
                return anchorDto;
            } else {
                throw new ServiceException(Constants.CODE_600.getCode(), "密码错误");
            }
        } else {
            throw new ServiceException(Constants.CODE_600.getCode(), "用户名不存在");
        }
    }

    //主播个人信息维护
    @Override
    public String modify(Anchor anchor) {
        Anchor anchor1 = anchorMapper.selectById(anchor.getId());
        System.out.println(anchor1+"WSssssssssssssss");
        if (anchor1 == null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "操作失败");
        }
        //用户名正则，3到16位（字母，数字，下划线，减号）
        Pattern pUsername = Pattern.compile("^[a-zA-Z0-9_-||\u4e00-\u9fa5]{3,16}$");
        Matcher pu = pUsername.matcher(anchor.getUsername());
        //匹配密码格式,4-16位且不能含有中文
        Pattern pPassword = Pattern.compile("^[^\\u4e00-\\u9fa5]{3,16}$");
        Matcher pp = pPassword.matcher(anchor.getPassword());
        //匹配邮箱的格式
        Pattern pEmail = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        Matcher me = pEmail.matcher(anchor.getEmail());
        if (!pu.matches()) {
        }
        if (!pp.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "密码格式有误,请输入4-16位且不能含有中文的有效字符!");
        }
        if (!me.matches()) {
            throw new ServiceException(Constants.CODE_600.getCode(), "请输入正确的邮箱!");
        }
        Anchor anchorN = getOne(new QueryWrapper<Anchor>().eq("username", anchor.getUsername()));
        Anchor anchorE = getOne(new QueryWrapper<Anchor>().eq("email", anchor.getEmail()));
        if (anchorN != null && !anchorN.getUsername().equals(anchor1.getUsername())) {
            throw new ServiceException(Constants.CODE_600.getCode(), "主播用户名已存在");
        } else if (anchorE != null && !anchorE.getEmail().equals(anchor1.getEmail())) {
            throw new ServiceException(Constants.CODE_600.getCode(), "主播邮箱已存在");
        }
        User user = userMapper.selectById(anchor.getUid());
        BeanUtils.copyProperties(anchor, user);
        user.setId(anchor.getUid());
        iUserService.modify(user);
        updateById(anchor);
        return "修改成功";
    }


    //获取博主信息,根据用户名查询
    @Override
    public Anchor mine(String username) {
        QueryWrapper<Anchor> anchorQueryWrapper = new QueryWrapper<>();
        anchorQueryWrapper.eq("username", username);
        Anchor anchor = getOne(anchorQueryWrapper);
        if (anchor == null) {
            throw new ServiceException(Constants.CODE_600.getCode(), "该用户不存在!");
        }
        return anchor;
    }

    //获取全部主播信息
    @Override
    public IPage<Anchor> selectByPage(Integer currentPage, Integer pageSize) {
        QueryWrapper<Anchor> pageWrapper = new QueryWrapper<>();
        //第一个参数为查询第几页,第二个参数为每页多少条记录
        Page<Anchor> page = new Page<>(currentPage, pageSize);
        IPage<Anchor> anchorIPage = anchorMapper.selectPage(page, pageWrapper);
        //sk
        anchorIPage.setTotal(anchorIPage.getTotal());
        return anchorIPage;
    }

    //查询6个最热门主播的热度值,粉丝数，用户名
    @Override
    public List<Anchor> getSixPopularity() {
        QueryWrapper<Anchor> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("popularity")
                .select(Anchor.class, anchorInfo -> anchorInfo.getColumn().equals("id") ||
                        anchorInfo.getColumn().equals("username") || anchorInfo.getColumn()
                        .equals("popularity") || anchorInfo.getColumn().equals("fans"));
        List<Anchor> anchorList = anchorMapper.selectList(queryWrapper);
        if (anchorList.size() > 6) {
            return anchorList.subList(0, 6);
        }
        return anchorList;
    }

    @Override
    public showAnchorDto getsix() {
        return new showAnchorDto(anchorMapper.getSixName(), anchorMapper.getSixPopularity(), anchorMapper.getSixFans());
    }

    //提现 提现规则：虚拟币：现金=2：1
    @Override
    public String cash(Integer Aid, Double cash) {
        //获取主播对象
        Anchor anchor = anchorMapper.selectById(Aid);
        //查询虚拟币
        Double virtualUrrency = anchor.getVirtualUrrency();
        //判断提现金额是否合法
        if(cash<0&&(virtualUrrency-2*cash)<0){
            throw new ServiceException(Constants.CODE_600.getCode(), "提现超额!");
        }
        //修改数据库
        anchor.setVirtualUrrency(virtualUrrency-2*cash);
        anchorMapper.updateById(anchor);
        return "提现成功!";
    }


}
