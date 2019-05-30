package com.example.demo.controller;

import com.example.demo.dao.Recruit;
import com.example.demo.dao.User;
import com.example.demo.service.ICityService;
import com.example.demo.service.IRecruitService;
import com.example.demo.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RecruitController {
    @Resource
    private IRecruitService iRecruitService;

    @Resource
    private ICityService iCityService;

    @Resource
    private IUserService iUserService;

    @RequestMapping("/recruit_summit_OK")
    @ResponseBody
    public Object Recruit_summit(HttpServletRequest request,@RequestParam("title")String title, @RequestParam("re_sex")String re_sex, @RequestParam("location")String location, @RequestParam("work_time")String work_time, @RequestParam("num")int num, @RequestParam("salary")String salary, @RequestParam("city")String city, @RequestParam("school")String school, @RequestParam("content")String content){
        Cookie[] cookie= request.getCookies();
        String Sid="";
        if(cookie!=null)
            for(Cookie cookie1:cookie)
            {
                if(cookie1.getName().equals("id")){
                    Sid=cookie1.getValue();
                }
            }
            int id= Integer.parseInt(Sid);
       iRecruitService.insertRecruit(num,salary,content,location,id,work_time,title,re_sex,city,school);
       return "11111";
    }
    @RequestMapping("/select_recruit")
    @ResponseBody
    public Object Select_recruit(@RequestParam(value = "pn",defaultValue ="1")int pn){
        PageHelper.startPage(pn,7);
        List<Recruit> recruits=iRecruitService.selectRecruit();
        for(Recruit recruit:recruits) {
            Date date = recruit.getCreat_time();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_now = new Date();
        }

        PageInfo pageInfo=new PageInfo(recruits,7);

        return pageInfo;
    }

    @RequestMapping("/select_recruitBySearch")
    @ResponseBody
    public Object Select_recruitBySearch(@RequestParam(value = "pn",defaultValue ="1")int pn,@RequestParam("search_id")String search_id){
        PageHelper.startPage(pn,7);
        List<Recruit> recruits=iRecruitService.selectRecruitBySearch(search_id);
        for(Recruit recruit:recruits) {
            Date date = recruit.getCreat_time();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_now = new Date();
        }

        PageInfo pageInfo=new PageInfo(recruits,7);

        return pageInfo;
    }
    @RequestMapping("/select_recruitByCity")
    @ResponseBody
    public Object Select_recruitByCity(@RequestParam(value = "pn",defaultValue ="1")int pn,@RequestParam("city_id")int city_id){
        PageHelper.startPage(pn,7);
        String city=iCityService.selectCityById(city_id);
        List<Recruit> recruits=iRecruitService.selectRecruitByCity(city);
        for(Recruit recruit:recruits) {
            Date date = recruit.getCreat_time();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_now = new Date();
        }

        PageInfo pageInfo=new PageInfo(recruits,7);

        return pageInfo;
    }



    @RequestMapping("/select_recruitById")
    public String Select_recruitById(@RequestParam("id")int id, HttpServletRequest request){
        Recruit recruit=iRecruitService.selectRecruitById(id);
        request.setAttribute("Recruit",recruit );
        User user=iUserService.findUserById(30);
        request.setAttribute("User",user);
        return "User/recruit_detail";
    }

    @RequestMapping("/select_recruitByUid")
    @ResponseBody
    public List<Recruit> select_recruitByUid(@RequestParam("uid")int uid){
        return iRecruitService.selectRecruitByUid(uid);
    }

    @RequestMapping("/select_recruitById_person")
    public String Select_recruitById_person(@RequestParam("id")int id, HttpServletRequest request){
        Recruit recruit=iRecruitService.selectRecruitById(id);
        request.setAttribute("Recruit",recruit );
        return "User/recruit_detail_person";
    }

    @RequestMapping("/Admin_news")
    public String News(HttpServletRequest request){
        List<Recruit> recruitList=iRecruitService.selectRecruit();
        request.setAttribute("recruitList",recruitList);
        return "Admin/news";
    }
}
