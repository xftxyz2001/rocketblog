package com.xftxyz.rocketblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;
    
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1346802467@qq.com"); // 设置发件人
        message.setTo(to); // 设置收件人
        message.setSubject(subject); // 设置邮件主题
        message.setText(content); // 设置邮件内容
        // log.info(message.toString());
        javaMailSender.send(message);
    }

    
}
