package com.xftxyz.rocketblog.service;

public interface EmailService {
    // 发送邮件
    void sendSimpleMail(String to, String subject, String content);

    // 发送验证码
    void sendVerifyCode(String to);
}
