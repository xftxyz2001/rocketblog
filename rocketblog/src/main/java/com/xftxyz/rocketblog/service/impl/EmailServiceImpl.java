package com.xftxyz.rocketblog.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.service.EmailService;
import com.xftxyz.rocketblog.util.Utils;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    StringRedisTemplate redisTemplate;

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

    @Override
    public void sendVerifyCode(String to) {
        // 随机生成六位数验证码
        String code = Utils.getRandomString(EnvironmentVariables.CODE_LENGTH);

        // 发送邮件
        String content = String.format(EnvironmentVariables.EMAIL_CODE_CONTENT, code);
        sendSimpleMail(to, EnvironmentVariables.EMAIL_CODE_SUBJECT, content);

        // 存入redis
        redisTemplate.opsForValue().set(to, code, EnvironmentVariables.CODE_EXPIRE_TIME, TimeUnit.SECONDS);
    }

}
