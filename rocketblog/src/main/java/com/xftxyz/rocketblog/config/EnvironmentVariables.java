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
}
