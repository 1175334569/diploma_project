package com.example.demo.mapper;

import com.example.demo.dao.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {
    @Select("select *from city")
    List<City> selectCity();
    @Select("select city from city where id=#{id}")
    String selectCityById(int id);
}
