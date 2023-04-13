package com.xftxyz.rocketblog.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.user.FrequentOperationException;
import com.xftxyz.rocketblog.service.EmailService;
import com.xftxyz.rocketblog.util.Utils;

@Service
public class EmailServiceImpl implements EmailService {

    // 注入spring.mail.username
    @Value("${spring.mail.username}")
    String from;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 设置发件人
        message.setTo(to); // 设置收件人
        message.setSubject(subject); // 设置邮件主题
        message.setText(content); // 设置邮件内容
        // log.info(message.toString());
        javaMailSender.send(message);
    }

    @Override
    public void sendVerifyCode(String to) {
        // 检查60秒内是否已经发送过验证码
        if (redisTemplate.hasKey(to)) {
            // 获取验证码发送时间
            Long time = redisTemplate.getExpire(to);
            // 剩余等待时间
            long waitTime = EnvironmentVariables.CODE_EXPIRE_TIME - time;
            if (waitTime > 0) {
                throw new FrequentOperationException("验证码发送过于频繁，" + waitTime + "秒后再试");
            }
        }
        // 随机生成六位数验证码
        String code = Utils.getRandomString(EnvironmentVariables.CODE_LENGTH);

        // 发送邮件
        String content = String.format(EnvironmentVariables.EMAIL_CODE_CONTENT, code);
        sendSimpleMail(to, EnvironmentVariables.EMAIL_CODE_SUBJECT, content);

        // 存入redis
        redisTemplate.opsForValue().set(to, code, EnvironmentVariables.CODE_EXPIRE_TIME, TimeUnit.SECONDS);
    }

}
