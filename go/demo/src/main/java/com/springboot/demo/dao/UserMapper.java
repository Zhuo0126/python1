package com.springboot.demo.dao;

import com.springboot.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
//@Component
public interface UserMapper {
	User findByUserName(@Param("username") String username, @Param("password") String password);
}
