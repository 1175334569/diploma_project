package com.example.demo.service;

import com.example.demo.dao.City;

import java.util.List;

public interface ICityService {
    List<City> selectCity();
    String selectCityById(int id);
}
