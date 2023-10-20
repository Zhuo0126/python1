package com.springboot.demo.mapper;

import com.springboot.demo.model.Login;
import com.springboot.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

//@Component
@Mapper
public interface LoginMapper {
	ArrayList<Login> select(@Param("USERID") String USERID);
}
