package com.example.demo.service;

import com.example.demo.dao.Recruit;

import java.util.Date;
import java.util.List;

public interface IRecruitService {
    void insertRecruit(int num, String salary, String content, String location, int uid, String work_time, String tilte, String re_sex,String city, String school);
    List<Recruit> selectRecruit();
    Recruit selectRecruitById(int id);
    List<Recruit> selectRecruitByUid(int uid);
    List<Recruit> selectRecruitBySearch(String search);
    List<Recruit> selectRecruitByCity(String city);
}
