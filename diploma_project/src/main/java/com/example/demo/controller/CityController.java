package com.example.demo.controller;

import com.example.demo.service.ICityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CityController {
    @Resource
    private ICityService iCityService;

    @RequestMapping(value = {"/selectCity", "/city_display"})
    @ResponseBody
    public Object selectCity() {
        return iCityService.selectCity();

    }

}
