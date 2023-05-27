package com.example.canyon_gaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Msg;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.MsgMapper;
import com.example.canyon_gaming.service.IMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sen
 * @since 2023-05-27
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements IMsgService {
    @Resource
    MsgMapper msgMapper;

    //保存评论
    @Override
    public Msg saveComment(Msg msg) {
        if (StringUtils.isNotBlank(msg.getUsername())) {
            save(msg);
            return msg;
        } else {
            throw new ServiceException(Constants.CODE_600.getCode(), "数据异常!");
        }
    }

    //获取所有评论信息
    @Override
    public List<Msg> findAllMsgList() {
        if (msgMapper.selectList(null).size() > 0) {
            QueryWrapper<Msg> pMsgQueryWrapper = new QueryWrapper<>();
            pMsgQueryWrapper.eq("pid", 0);
            List<Msg> pMsg = msgMapper.selectList(pMsgQueryWrapper);
            for (Msg cMgs : pMsg) {
                QueryWrapper<Msg> cMsgQueryWrapper = new QueryWrapper<>();
                cMsgQueryWrapper.eq("pid", cMgs.getId());
                if (msgMapper.selectList(cMsgQueryWrapper).size() > 0) {
                    cMgs.setChildrenMsgList(msgMapper.selectList(cMsgQueryWrapper));
                }
            }
            return pMsg;
        } else {
            return null;
        }

    }

    //删除用户评论
    @Override
    public String deleteMsg(Integer id, Integer pid) {
        if (id != null && id > 0) {
            try {
                QueryWrapper<Msg> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", id).or().eq("pid", id);
                remove(queryWrapper);
                return "删除评论成功";
            } catch (Exception e) {
                throw new ServiceException(Constants.CODE_600.getCode(), "系统错误!");
            }
        }
        throw new ServiceException(Constants.CODE_600.getCode(), "数据异常!");
    }
}
