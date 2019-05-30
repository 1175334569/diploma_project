package com.example.demo.controller;

import com.example.demo.dao.School;
import com.example.demo.service.ISchoolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SchoolController {
    @Resource
    private ISchoolService iSchoolService;
    /*@RequestMapping("/hello")
    public String AllSchool(){
        StringBuffer school=new StringBuffer("澳门科技大学\t澳门大学\t");
        int start=0,end=0;
        while(school.indexOf("\t", start)>0) {
            end = school.indexOf("\t", start);
            iSchoolService.addSchool(school.substring(start, end), 33);
            start = end + 1;
            while(start==school.indexOf("\t",start))start++;
        }
        return "ok";
    }*/
/*    @RequestMapping("/")
    @ResponseBody
    public Object findAll(@RequestParam(value="pn",defaultValue = "1")int pn, Model model){
        PageHelper.startPage(pn,10);
        List<School> users=iSchoolService.selectSchool();
        PageInfo pageInfo=new PageInfo(users,10);
        model.addAttribute("users",pageInfo);
        return pageInfo;

    }*/

    @RequestMapping( value = {"/selectSchool","/school_display"})
    @ResponseBody
    public Object selectSchool(int cid){
        return iSchoolService.selectSchoolByCid(cid);
    }
}
