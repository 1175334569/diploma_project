package com.example.demo.controller;

import com.example.demo.dao.Recruit;
import com.example.demo.dao.Recruit_detail;
import com.example.demo.service.IRecruit_detailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class Recruit_detailController {
    @Resource
    private IRecruit_detailService iRecruit_detailService;

    @RequestMapping("/recruit_student")
    @ResponseBody
    public Object recruit_student(@RequestParam("recruit_id") int recruit_id, @RequestParam("uid") int uid) {
        iRecruit_detailService.InsertRecruit_detail(recruit_id, uid);
        return "111";
    }

    @RequestMapping("/deliver_recruitOrNot")
    @ResponseBody
    public Boolean deliver_recruitOrNot(@RequestParam("recruit_id") int recruit_id, @RequestParam("uid") int uid) {
        return iRecruit_detailService.SelectRecruit_detailByRidAndUid(recruit_id, uid);
    }

    @RequestMapping("/company_recruitOrNot")
    @ResponseBody
    public List<Recruit_detail> company_recruitOrNot(@RequestParam("recruit_id") int recruit_id) {


        List<Recruit_detail> recruit_details=iRecruit_detailService.SelectRecruit_detailByRid(recruit_id);
        System.out.println(recruit_details.toString());
        return iRecruit_detailService.SelectRecruit_detailByRid(recruit_id);
    }

    @RequestMapping("/user_apply")
    @ResponseBody
    public List<Recruit> User_apply(int id) {
        return iRecruit_detailService.SelectRecruit_detailByUid(id);
    }
}
