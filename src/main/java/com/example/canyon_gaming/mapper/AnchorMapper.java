package com.example.canyon_gaming.mapper;

import com.example.canyon_gaming.entity.Anchor;
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
 * @since 2023-05-03
 */
@Mapper
public interface AnchorMapper extends BaseMapper<Anchor> {

    int update(@Param("id") int id,@Param("phone") String phone,@Param("email") String email,@Param("password") String password);

    Anchor getName(@Param("id") Integer id, @Param("username") String username);
    Anchor getPhone(@Param("id") Integer id,@Param("phone") String phone);
    Anchor getEmail(@Param("id") Integer id,@Param("email") String email);
    Anchor getByUid(@Param("uid") Integer uid);
    List<Integer> getSixPopularity();
    List<Integer> getSixFans();
    List<String> getSixName();
    Anchor getByRoomId(@Param("room_id") String room_id);
}
