package com.example.canyon_gaming.mapper;

import com.example.canyon_gaming.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-05-13
 */
@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {
    //用户申请
//    String apply(@Param("uid") Integer uid,@Param("theme") String theme);

    //拒绝申请
    String reapply(@Param("uid") Integer uid);
}
