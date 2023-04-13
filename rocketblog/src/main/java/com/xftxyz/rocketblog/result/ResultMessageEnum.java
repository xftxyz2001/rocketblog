package com.xftxyz.rocketblog.result;

public enum ResultMessageEnum {
    // 未知错误
    UNKNOWN_ERROR(-1, "未知错误"),
    // 成功
    SUCCESS(0, "成功"),
    // 失败
    FAIL(1, "失败"),

    // 用户错误1xx
    USER_NOT_LOGIN(100, "用户未登录"),
    CAPTCHA_ERROR(101, "验证码错误"),
    EMAIL_OR_PASSWORD_ERROR(102, "邮箱或密码错误"),
    PASSWORD_ERROR(103, "密码错误"),
    USER_NOT_EXIST(104, "用户不存在"),
    EMAIL_EXIST(105, "邮箱已被注册"),
    ILLEGAL_OPERATION(106, "非法操作"),
    EMAIL_SEND_ERROR(107, "邮件发送失败"),
    CAPTCHA_SEND_FREQUENTLY(108, "验证码发送过于频繁"),


    // 博客错误2xx
    SELF_OPERATION(200, "不能对自己进行此操作"),
    ALREADY_DONE(201, "已经执行过该操作"),
    BLOG_NOT_EXIST(202, "博客不存在"),

    // 图片错误3xx
    IMAGE_ERROR(300, "图片错误"),

    // 聊天错误4xx
    NO_CHAT(400, "没有与该用户的聊天"),

    // 其他错误
    PARAM_ERROR(500, "非法参数"),
    // 参数校验错误
    PARAM_VALID_ERROR(501, "参数校验错误");

    private Integer code;
    private String message;

    ResultMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
