package com.springboot.demo.mapper;

import com.springboot.demo.model.Drink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DrinkExtMapper extends DrinkMapper{
    List<Drink> findAll();
}