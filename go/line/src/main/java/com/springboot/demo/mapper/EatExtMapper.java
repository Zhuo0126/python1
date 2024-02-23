package com.springboot.demo.mapper;

import com.springboot.demo.model.Eat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EatExtMapper extends EatMapper{
    List<Eat> findAll();
}