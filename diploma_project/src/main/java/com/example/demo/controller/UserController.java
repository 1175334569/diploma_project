package com.example.demo.controller;

import com.example.demo.dao.User;
import com.example.demo.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Controller
public class UserController {
    @Resource
    private IUserService iUserService;

    @Resource
    public JavaMailSender javaMailSender;

  /*  @RequestMapping("/")
    public String findAll(@RequestParam(value="pn",defaultValue = "1")int pn, Model model){
        PageHelper.startPage(pn,5);
        List<User> users=iUserService.findAll();
        PageInfo pageInfo=new PageInfo(users,5);
        model.addAttribute("users",pageInfo);
        return "project";

    }*/
  //用户登录
  @RequestMapping("/findUser")
  @ResponseBody
    public Object findUser(@RequestParam("email")String email, @RequestParam("password")String password, HttpServletResponse response){
      if(email.equals("admin@qq.com")&&password.equals("123456")){
          return "1";
      }
      else {
      email=email.toLowerCase();
      User user=new User();
      user=iUserService.findUser(email,password);
      if(user!=null){
          Cookie cookie=new Cookie("username",user.getUsername());
          Cookie cookie1=new Cookie("password",user.getPassword());
          Cookie cookie2=new Cookie("email",user.getEmail());
          Cookie cookie3=new Cookie("identity",user.getIdentity());
          Cookie cookie4=new Cookie("id",String.valueOf(user.getId()));

          response.addCookie(cookie);
          response.addCookie(cookie1);
          response.addCookie(cookie2);
          response.addCookie(cookie3);
          response.addCookie(cookie4);

          return user;
      }

      else return null;
      }
  }
//用户注册
  @RequestMapping("/register")
  @ResponseBody
  @Transactional
    public Object register(@RequestParam("re_username")String re_username,@RequestParam("re_password")String re_password,@RequestParam("re_email")String re_email,@RequestParam("re_identity")String re_identity) throws Exception{
      re_email=re_email.toLowerCase();
       if(iUserService.findUserByEmail(re_email)!=null){
        if(iUserService.findUserByEmail(re_email).getCode()=="0") return "error1";
        else {
            try {
                Random random = new Random();
                StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTabcdefghijklmnopqrst123456789");
                StringBuffer code = new StringBuffer();
                for (int i = 0; i < 16; i++) {
                    code.append(stringBuffer.charAt(random.nextInt(49)));
                }
                iUserService.updateCode(code.toString(),re_email);
                //发送邮件
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("2499584438@qq.com"); // 邮件发送者
                message.setTo(re_email); // 邮件接受者
                message.setSubject("（高校兼职平台）欢迎注册激活通知"); // 主题
                message.setText(
                        "这是一封注册激活邮件，你必须点击下边的链接（或将链接复制到浏览器地址栏中访问）才能激活你的账号并设置密码。如果你收到了多封激活邮件，请以最后一封为准。\n" +
                                "\n" +
                                "激活链接： http://localhost/activation?email=" + re_email + "&code=" + code.toString() + "\n" +
                                "\n" +
                                "如果您点击链接后无法打开页面，可以将以上地址复制到浏览器地址栏中打开。"); // 内容

                javaMailSender.send(message);
                return "OK";
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                System.out.println("注册失败");
                return "error2";
            }
        }
       }
       else {
           try {
               Random random = new Random();
               StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTabcdefghijklmnopqrst123456789");
               StringBuffer code = new StringBuffer();
               for (int i = 0; i < 16; i++) {
                   code.append(stringBuffer.charAt(random.nextInt(49)));
               }
               iUserService.insertUser(re_username, re_password, re_email, code.toString(),re_identity);
               //发送邮件
               SimpleMailMessage message = new SimpleMailMessage();
               message.setFrom("2499584438@qq.com"); // 邮件发送者
               message.setTo(re_email); // 邮件接受者
               message.setSubject("（高校兼职平台）欢迎注册激活通知"); // 主题
               message.setText(
                       "这是一封注册激活邮件，你必须点击下边的链接（或将链接复制到浏览器地址栏中访问）才能激活你的账号并设置密码。如果你收到了多封激活邮件，请以最后一封为准。\n" +
                       "\n" +
                       "激活链接： http://localhost/activation?email=" + re_email + "&code=" + code.toString() + "\n" +
                       "\n" +
                       "如果您点击链接后无法打开页面，可以将以上地址复制到浏览器地址栏中打开。"); // 内容

               javaMailSender.send(message);
               return "OK";
           }catch (Exception e){
               TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           System.out.println(e.getMessage());
           return "error2";
       }
       }
  }
  //激活操作
  @RequestMapping("/activate")
    @ResponseBody
    public Object activate(@RequestParam("email")String email,@RequestParam("code")String code){
      email=email.toLowerCase();
      if(iUserService.findUserByEmailAndCode(email,code)!=null){
          iUserService.updateCode("0",email);
          return "OK";
      }
      else return "error";
  }
/*忘记密码*/
  @RequestMapping("/forget_email_op")
    @ResponseBody
    public Object forget_email_op(@RequestParam("email")String email)throws Exception{
      email=email.toLowerCase();
       if(iUserService.findUserByEmailAndCode(email,"0")!=null){
           try {
               Random random = new Random();
               StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTabcdefghijklmnopqrst123456789");
               StringBuffer re_code = new StringBuffer();
               for (int i = 0; i < 16; i++) {
                   re_code.append(stringBuffer.charAt(random.nextInt(49)));
               }
               iUserService.updateReCode(re_code.toString(),email);
               SimpleMailMessage message = new SimpleMailMessage();
               message.setFrom("2499584438@qq.com"); // 邮件发送者
               message.setTo(email); // 邮件接受者
               message.setSubject("（高校兼职平台）密码修改通知"); // 主题
               message.setText("亲爱的"+iUserService.findUserByEmail(email).getUsername()+"：\n" +
                       "这是一封密码修改邮件，你必须点击下边的链接（或将链接复制到浏览器地址栏中访问）才能修改您的密码。如果你收到了多封修改密码邮件，请以最后一封为准。\n" +
                       "\n" +
                       "重置链接： http://localhost/resetPassword?email=" + email + "&code=" + re_code.toString() + "\n" +
                       "\n" +
                       "如果您点击链接后无法打开页面，可以将以上地址复制到浏览器地址栏中打开。"); // 内容

               javaMailSender.send(message);
               return "OK";

           }catch (Exception e){
               return "error2";
           }

       }
       else {
           return "error1";
       }
  }

  /*判断页面*/
    @RequestMapping("/jude_reset_password")
    @ResponseBody
    public Object jude_reset_password(@RequestParam("email")String email,@RequestParam("re_code")String re_code){
        if(iUserService.findUserByEmailAndReCode(email,re_code)!=null){
            return "OK";
        }
        else return "error";
    }
  /*重置密码*/
  @RequestMapping("/reset_password")
    @ResponseBody
    public Object reset_password(@RequestParam("new_password")String new_password,@RequestParam("email")String email,@RequestParam("re_code")String re_code)throws SQLException {
      email=email.toLowerCase();
      Random random = new Random();
      StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTabcdefghijklmnopqrst123456789");
      StringBuffer code = new StringBuffer();
      for (int i = 0; i < 16; i++) {
          code.append(stringBuffer.charAt(random.nextInt(49)));
      }
          try {
              iUserService.updatePassword(new_password,email);
              iUserService.updateReCode(code.toString(),email);
              return "OK";
          }catch (Exception e){
              return "error";
          }
  }

  //获取用户数据
    @RequestMapping("/user_set")
    public Object User_set(HttpServletRequest request){
        Cookie[] cookie= request.getCookies();
        String email="",password="",identity="";
        if(cookie!=null)
            for(Cookie cookie1:cookie)
            {
                if(cookie1.getName().equals("email")){
                     email=cookie1.getValue();
                }
                if(cookie1.getName().equals("password")){
                    password=cookie1.getValue();
                }
                if(cookie1.getName().equals("identity")){
                    identity=cookie1.getValue();
                }
            }
        User user=new User();
        user=iUserService.findUser(email,password);
        request.setAttribute("user",user);
        if(identity.equals("0"))
         return "User/user_set";
        else return "Company/company_set";
    }

    @RequestMapping("/set_company")
    public String set_company(HttpServletRequest request){
        Cookie[] cookie= request.getCookies();
        String identity="";
        if(cookie!=null)
            for(Cookie cookie1:cookie)
            {
                if(cookie1.getName().equals("identity")){
                    identity=cookie1.getValue();
                }
            }
        if(identity.equals("0"))
            return "User/user_centre";
        else  return "Company/company_centre";
    }

    @RequestMapping("/update_user")
    @ResponseBody
    public Object update_user(@RequestParam("username")String username,@RequestParam("sex")String sex,@RequestParam("email")String email,@RequestParam("city")String city,@RequestParam("school")String school,@RequestParam("specialty")String specialty,@RequestParam("skill")String skill,@RequestParam("phone")String phone)throws SQLException{
      try{
          iUserService.update_user(username,sex,city,school,specialty,skill,email,phone);
          return "OK";
      }catch (Exception e){
          return "error";
      }
    }

    @RequestMapping("/update_company")
    @ResponseBody
    public Object update_company(@RequestParam("username")String username,@RequestParam("sex")String sex,@RequestParam("phone")String phone,@RequestParam("company")String company,@RequestParam("address")String address,@RequestParam("introdution")String introdution,@RequestParam("email")String email)throws SQLException{
        try{
            iUserService.update_company(username,sex,phone,company,address,introdution,email);
            return "OK";
        }catch (Exception e){
            return "error";
        }
    }

    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(@RequestParam("id")int id){
      return iUserService.findUserById(id);
    }

    @RequestMapping("/reset_passwordById")
    @ResponseBody
    public String reset_passwordById(String newpassword ,HttpServletRequest request){
        Cookie[] cookie= request.getCookies();
        String sid="";
        if(cookie!=null)
            for(Cookie cookie1:cookie)
            {
                if(cookie1.getName().equals("id")){
                    sid=cookie1.getValue();
                }
            }
            int id=Integer.parseInt(sid);
            iUserService.updatePasswordById(newpassword,id);

      return "sss";
    }

    @RequestMapping("/Admin_user")
    public String User(HttpServletRequest request){
        List<User> userList=iUserService.findAllById(0);
        request.setAttribute("Users",userList);
        return "Admin/user";
    }

    @RequestMapping("/Admin_company")
    public String Company(HttpServletRequest request)
    {
        List<User> userList=iUserService.findAllById(1);
        request.setAttribute("Users",userList);
        return "Admin/company";
    }

    @RequestMapping("/delectUser")
    @ResponseBody
    public String delectUser(@RequestParam("id")int id){
      iUserService.delectUser(id);
      return "11";
    }
}
