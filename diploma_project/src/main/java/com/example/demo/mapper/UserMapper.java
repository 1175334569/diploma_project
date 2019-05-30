package com.example.demo.mapper;

import com.example.demo.dao.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /*注册*/
    @Insert("insert into user(username,password,email,code,identity) values(#{username},#{password},#{email},#{code},#{identity})")
    void insertUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("code") String code,@Param("identity")String identity);

    /*查询所有用户*/
    @Select("SELECT * FROM user")
    List<User> findAll();

    /*判断用户登录*/
    @Select("select * from user where email=#{email} and password =#{password} and code=#{code}")
    User findUser(@Param("email") String email, @Param("password") String password, @Param("code") String code);

    /*根据邮箱查询*/
    @Select("select * from user where email=#{email}")
    User findUserByEmail(String email);

    /*根据邮箱和code查询*/
    @Select("select * from user where email=#{email} and code=#{code}")
    User findUserByEmailAndCode(@Param("email") String email, @Param("code") String code);

    /*根据用户ID查询*/
    @Select("select * from user where id=#{id}")
    User findUserById(int id);

    /*根据邮箱和re_code查询*/
    @Select("select * from user where email=#{email} and re_code=#{re_code}")
    User findUserByEmailAndReCode(@Param("email") String email, @Param("re_code") String re_code);

    /*更新code*/
    @Update("update user set code=#{code} where email=#{email}")
    void updateCode(@Param("code") String code, @Param("email") String email);

    /*更新re_code*/
    @Update("update user set re_code=#{re_code} where email=#{email}")
    void updateReCode(@Param("re_code") String re_code, @Param("email") String email);

    /*更新密码*/
    @Update("update user set password=#{password} where email=#{email}")
    void updatePassword(@Param("password") String password, @Param("email") String email);

    /*更新用户信息*/
    @Update("update user set username=#{username},sex=#{sex},city=#{city},school=#{school},specialty=#{specialty},skill=#{skill},phone=#{phone} where email=#{email}")
    void update_user(@Param("username") String username, @Param("sex") String sex, @Param("city") String city, @Param("school") String school, @Param("specialty") String specialty, @Param("skill") String skill, @Param("email") String email,@Param("phone")String phone);

    /*更新企业信息*/
    @Update("update user set username=#{username},sex=#{sex},phone=#{phone},company=#{company},address=#{address},introdution=#{introdution} where email=#{email}")
    void update_company(@Param("username") String username, @Param("sex") String sex, @Param("phone") String phone, @Param("company") String company, @Param("address") String address, @Param("introdution") String introdution,@Param("email")String email);

    @Update("update user set password=#{password} where id=#{id}")
    void updatePasswordById(@Param("password") String password, @Param("id") int id);

    /*删除用户*/
    @Delete("delete from user where id=#{id}")
    void delectUser(@Param("id") int id);

    /*按身份查询所有用户*/
    @Select("SELECT * FROM user where identity=#{id}")
    List<User> findAllById(int id);

}