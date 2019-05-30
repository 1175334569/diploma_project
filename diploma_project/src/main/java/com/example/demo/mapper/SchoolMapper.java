package com.example.demo.mapper;

import com.example.demo.dao.School;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SchoolMapper {
    @Insert("insert into school(schoolname,cid) values(#{schoolname},#{cid})")
    void addSchool(@Param("schoolname")String schoolname,@Param("cid")int cid);
    @Select("select * from school")
    List<School> selectSchool();
    @Select("select schoolname from school where cid=#{cid}")
    List<String> selectSchoolByCid(int cid);
}
