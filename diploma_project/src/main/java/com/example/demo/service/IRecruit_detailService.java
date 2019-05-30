package com.example.demo.service;

import com.example.demo.dao.Recruit;
import com.example.demo.dao.Recruit_detail;

import java.util.List;

public interface IRecruit_detailService {
    void InsertRecruit_detail(int recruit_id,int uid);
    Boolean SelectRecruit_detailByRidAndUid(int recruit_id,int uid);
    List<Recruit_detail> SelectRecruit_detailByRid(int recruit_id);
    List<Recruit> SelectRecruit_detailByUid(int uid);
}
