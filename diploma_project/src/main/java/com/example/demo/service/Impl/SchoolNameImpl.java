package com.example.demo.service.Impl;

import com.example.demo.dao.School;
import com.example.demo.mapper.SchoolMapper;
import com.example.demo.service.ISchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolNameImpl implements ISchoolService {

    @Resource
    private SchoolMapper schoolMapper;
    @Override
    public void addSchool(String schoolname, int cid) {
        schoolMapper.addSchool(schoolname,cid);
    }

    @Override
    public List<School> selectSchool() {
        return schoolMapper.selectSchool();
    }

    @Override
    public List<String> selectSchoolByCid(int cid) {
        return schoolMapper.selectSchoolByCid(cid);
    }
}
