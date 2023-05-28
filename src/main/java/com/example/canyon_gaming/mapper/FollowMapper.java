package com.example.canyon_gaming.mapper;

import com.example.canyon_gaming.entity.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sen
 * @since 2023-05-21
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    List<Integer> getByUid(@Param("uid") Integer uid);
}
