package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest{

    @Autowired
    public JavaMailSender javaMailSender;
    @Test
    public void sendSimpleMail() throws MailException {
        /*        Date d = new Date();
                System.out.println(d);
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String currdate = format.format(d);
               System.out.println("现在的日期是：" + currdate);
              Calendar ca = Calendar.getInstance();
               ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
                 d = ca.getTime();
                 String enddate = format.format(d);
                System.out.println("增加天数以后的日期：" + enddate);*/
     /*   String str = "2019-02-21 10:10:20";
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(date);
        System.out.println(date.getTime());
        Date now=new Date();
        System.out.println((now.getTime()-date.getTime())/(24*60*60*1000));*/
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currdate = format.format(d);
        System.out.println(currdate);
    }
}
