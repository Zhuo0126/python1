package com.springboot.demo.dao;

import com.springboot.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Mapper
@Component
public interface UserMapper {
	User findByUserName(String userName,String Password);
}
