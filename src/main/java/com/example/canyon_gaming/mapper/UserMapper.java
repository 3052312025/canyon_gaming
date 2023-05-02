package com.example.canyon_gaming.mapper;

import com.example.canyon_gaming.entity.User;
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
 * @since 2023-05-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public List<User> getUsersByLevel(@Param("level") Integer level);
    int update(@Param("username") String username,@Param("phone") String phone,@Param("email") String email,@Param("password") String password);
    User getName(@Param("id") Integer id,@Param("username") String username);
    User getPhone(@Param("id") Integer id,@Param("phone") String phone);
    User getEmail(@Param("id") Integer id,@Param("email") String email);
}
