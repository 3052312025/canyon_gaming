package com.example.canyon_gaming.mapper;

import com.example.canyon_gaming.entity.Worktime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-05-21
 */
@Mapper
public interface WorktimeMapper extends BaseMapper<Worktime> {

    //主播个人排班展示
    List<Worktime> selectByAid(@Param("aid") Integer aid);

    //展示排班 日期已经排序
    List<Worktime> selectAll();

    //申请排班列表展示
    List<Worktime> selectByState(@Param("state") Integer state);


}
