package com.springboot.demo.mapper;

import com.springboot.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user WHERE username = #{username}")
	User findByUserName(@Param("username") String username, @Param("password") String password);

	@Select("SELECT * FROM user")
	List<User> findAll();
}
