package com.xftxyz.rocketblog.config;

public interface EnvironmentVariables {
    // COOKIE中token的key
    String COOKIE_TOKEN = "token";
    // SESSION中用户的key
    String SESSION_USER = "user";

    // 验证码位数
    int CODE_LENGTH = 6;
    // SESSION中验证码的key
    String SESSION_CODE = "code";

    // 验证码邮件主题
    String EMAIL_CODE_SUBJECT = "火箭博客验证码";
    // 验证码邮件内容
    String EMAIL_CODE_CONTENT = "您的验证码为：";

    // 找回密码邮件主题
    String EMAIL_FORGET_SUBJECT = "火箭博客找回密码";
    // 找回密码邮件内容
    String EMAIL_FORGET_CONTENT = "您的密码是：";

    // 密码掩码
    String PASSWORD_MASK = "********";

}
