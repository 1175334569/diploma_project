package com.example.demo.service;

import com.example.demo.dao.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findUser(String email,String password);
    User findUserByEmail(String email);
    void insertUser(String username,String password,String email,String code,String identity);
    User findUserByEmailAndCode(String email,String code);
    User findUserByEmailAndReCode(String email,String re_code);
    void updateCode(String code,String email);
    void updateReCode(String re_code,String email);
    void updatePassword(String new_password,String email);
    void update_user( String username,String sex,  String city,String school, String specialty, String skill, String email,String phone);
    void update_company(String username, String sex,  String phone, String company,  String address, String introdution,String email);
    User findUserById(int id);
    void updatePasswordById(String password,int id);
    void delectUser(int id);
    List<User> findAllById(int id);
}
