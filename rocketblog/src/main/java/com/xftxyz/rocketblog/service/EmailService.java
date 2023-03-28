package com.xftxyz.rocketblog.service;

public interface EmailService {
    // 发送邮件
    void sendSimpleMail(String to, String subject, String content);
}
