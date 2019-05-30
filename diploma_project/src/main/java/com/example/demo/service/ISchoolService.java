package com.example.demo.service;

import com.example.demo.dao.School;

import java.util.List;

public interface ISchoolService {
    void addSchool(String schoolname,int cid);
    List<School> selectSchool();
    List<String> selectSchoolByCid(int cid);
}
