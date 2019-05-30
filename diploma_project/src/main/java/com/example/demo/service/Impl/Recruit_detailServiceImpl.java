package com.example.demo.service.Impl;

import com.example.demo.dao.Recruit;
import com.example.demo.dao.Recruit_detail;
import com.example.demo.mapper.Recruit_detailMapper;
import com.example.demo.service.IRecruit_detailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Recruit_detailServiceImpl implements IRecruit_detailService {

    @Resource
    private Recruit_detailMapper recruit_detailMapper;
    @Override
    public void InsertRecruit_detail(int recruit_id, int uid) {
        recruit_detailMapper.InsertRecruit_detail(recruit_id,uid);
    }

    @Override
    public Boolean SelectRecruit_detailByRidAndUid(int recruit_id, int uid) {
        return recruit_detailMapper.SelectRecruit_detailByRidAndUid(recruit_id,uid);
    }

    @Override
    public List<Recruit_detail> SelectRecruit_detailByRid(int recruit_id) {
        return  recruit_detailMapper.SelectRecruit_detailByRid(recruit_id);
    }

    @Override
    public List<Recruit> SelectRecruit_detailByUid(int uid) {
        return recruit_detailMapper.SelectRecruit_detailByUid(uid);
    }
}
