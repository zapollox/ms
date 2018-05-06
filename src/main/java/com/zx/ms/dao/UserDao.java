package com.zx.ms.dao;

import com.zx.ms.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by DELL on 2018/4/27.
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") Integer id);
}
