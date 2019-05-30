package com.example.demo.mapper;

import com.example.demo.dao.Recruit;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

@Mapper
public interface RecruitMapper {
    @Insert("insert into recruit(num,salary,content,location,uid,work_time,title,re_sex,creat_time,city,school) values(#{num},#{salary},#{content},#{location},#{uid},#{work_time},#{title},#{re_sex},#{creat_time},#{city},#{school})")
    void insertRecruit(@Param("num")int num, @Param("salary")String salary, @Param("content")String content, @Param("location") String location, @Param("uid")int uid, @Param("work_time")String work_time, @Param("title")String title, @Param("re_sex")String re_sex, @Param("creat_time")Date creat_time, @Param("city")String city, @Param("school")String school);
    @Select("select *from recruit order by id desc")
    List<Recruit> selectRecruit();
    @Select("select *from recruit where id=#{id}")
    Recruit selectRecruitById(int id);
    @Select("select *from recruit where uid=#{uid}")
    List<Recruit> selectRecruitByUid(int uid);
    @Select("select *from recruit where title LIKE concat('%',#{search},'%')")
    List<Recruit> selectRecruitBySearch(String search);
    @Select("select *from recruit where city=#{city}")
    List<Recruit> selectRecruitByCity(String city);
}
