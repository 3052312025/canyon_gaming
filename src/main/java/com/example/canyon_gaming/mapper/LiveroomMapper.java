package com.example.canyon_gaming.mapper;

import com.example.canyon_gaming.entity.Liveroom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.canyon_gaming.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-05-05
 */
@Mapper
public interface LiveroomMapper extends BaseMapper<Liveroom> {

    public List<Liveroom> selectAll();

    Liveroom getByRoomID(@Param("roomid") String roomid);

}
