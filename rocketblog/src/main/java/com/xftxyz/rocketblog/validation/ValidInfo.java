package com.xftxyz.rocketblog.validation;

public interface ValidInfo {
    // 用户ID不能为空
    String USER_ID_NOT_NULL = "用户ID不能为空";
    // 用户ID不能小于1
    String USER_ID_LESS_THAN_ONE = "目标用户ID不合法";

    // 博客ID不能为空
    String BLOG_ID_NOT_NULL = "博客ID不能为空";
    // 博客ID不能小于1
    String BLOG_ID_LESS_THAN_ONE = "目标博客ID不合法";

    // 页码小于1
    String PAGE_LESS_THAN_ONE = "页码从1开始";

    // 评论、消息
    String CONTENT_NOT_NULL = "内容不能为空";// 不能为空
    int CONTENT_MAX_LENGTH = 500; // 最大长度

    // 密码最小长度
    int MIN_PASSWORD_LENGTH = 8;
    // 密码最大长度
    int MAX_PASSWORD_LENGTH = 16;

}
