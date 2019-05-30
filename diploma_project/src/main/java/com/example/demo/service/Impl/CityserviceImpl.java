package com.example.demo.service.Impl;

import com.example.demo.dao.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.ICityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CityserviceImpl implements ICityService {
    @Resource
    private CityMapper cityMapper;
    @Override
    public List<City> selectCity() {
        return cityMapper.selectCity();
    }

    @Override
    public String selectCityById(int id) {
        return cityMapper.selectCityById(id);
    }
}
