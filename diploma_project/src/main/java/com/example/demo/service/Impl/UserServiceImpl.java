package com.example.demo.service.Impl;

import com.example.demo.dao.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findUser(String email, String password) {
        return userMapper.findUser(email,password,"0");
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public void insertUser(String username, String password, String email,String code,String identity) {
        userMapper.insertUser(username,password,email,code,identity);
    }

    @Override
    public User findUserByEmailAndCode(String email, String code) {
        return userMapper.findUserByEmailAndCode(email,code);
    }

    @Override
    public User findUserByEmailAndReCode(String email, String re_code) {
        return userMapper.findUserByEmailAndReCode(email,re_code);
    }

    @Override
    public void updateCode(String code, String email) {
        userMapper.updateCode(code,email);
    }

    @Override
    public void updateReCode(String re_code, String email) {
        userMapper.updateReCode(re_code,email);
    }

    @Override
    public void updatePassword(String new_password, String email) {
        userMapper.updatePassword(new_password,email);
    }

    @Override
    public void update_user(String username, String sex, String city, String school, String specialty, String skill, String email,String phone) {
        userMapper.update_user(username,sex,city,school,specialty,skill,email,phone);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void updatePasswordById(String password, int id) {
        userMapper.updatePasswordById(password,id);
    }

    @Override
    public void delectUser(int id) {
        userMapper.delectUser(id);
    }

    @Override
    public List<User> findAllById(int id) {
        return userMapper.findAllById(id);
    }

    @Override
    public void update_company(String username, String sex, String phone, String company, String address, String introdution, String email) {
        userMapper.update_company(username,sex,phone,company,address,introdution,email);
    }
}
