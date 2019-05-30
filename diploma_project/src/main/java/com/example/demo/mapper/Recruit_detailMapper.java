package com.example.demo.mapper;

import com.example.demo.dao.Recruit;
import com.example.demo.dao.Recruit_detail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Recruit_detailMapper {
    @Insert("insert into recruit_detail(recruit_id,uid) values(#{recruit_id},#{uid})")
    void InsertRecruit_detail(@Param("recruit_id") int recruit_id, @Param("uid") int uid);
    @Select("select id from recruit_detail where recruit_id=#{recruit_id} and uid=#{uid}")
    Boolean SelectRecruit_detailByRidAndUid(@Param("recruit_id") int id,@Param("uid")int uid);
    @Select("select * from recruit_detail where recruit_id=#{recruit_id}")
    List<Recruit_detail> SelectRecruit_detailByRid(int recruit_id);
    @Select("select * from recruit,recruit_detail where recruit_detail.recruit_id=recruit.id and recruit_detail.uid=#{uid}")
    List<Recruit> SelectRecruit_detailByUid(int uid);
}
