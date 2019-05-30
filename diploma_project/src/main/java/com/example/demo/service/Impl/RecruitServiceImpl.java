package com.example.demo.service.Impl;

import com.example.demo.dao.Recruit;
import com.example.demo.mapper.RecruitMapper;
import com.example.demo.service.IRecruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RecruitServiceImpl implements IRecruitService {
    @Resource
    private RecruitMapper recruitMapper;

    @Override
    public void insertRecruit(int num, String salary, String content, String location, int uid, String work_time, String tilte, String re_sex, String city, String school) {
        Date date=new Date();
        recruitMapper.insertRecruit(num,salary,content,location,uid,work_time,tilte,re_sex,date,city,school);
    }

    @Override
    public List<Recruit> selectRecruit() {
        return recruitMapper.selectRecruit();
    }

    @Override
    public Recruit selectRecruitById(int id) {
        return recruitMapper.selectRecruitById(id);
    }

    @Override
    public List<Recruit> selectRecruitByUid(int uid) {
        return  recruitMapper.selectRecruitByUid(uid);
    }

    @Override
    public List<Recruit> selectRecruitBySearch(String search) {
        return recruitMapper.selectRecruitBySearch(search);
    }

    @Override
    public List<Recruit> selectRecruitByCity(String city) {
        return recruitMapper.selectRecruitByCity(city);
    }
}
