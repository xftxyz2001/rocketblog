package com.xftxyz.rocketblog.validation;

public interface ValidInfo {
    // 用户ID小于1
    String USER_ID_LESS_THAN_ONE = "目标用户ID不合法";
    
    // 页码小于1
    String PAGE_LESS_THAN_ONE = "页码从1开始";
    
    // 评论、消息最大长度
    int MAX_LENGTH = 500;

    // 密码最小长度
    int MIN_PASSWORD_LENGTH = 8;
    // 密码最大长度
    int MAX_PASSWORD_LENGTH = 16;

}
