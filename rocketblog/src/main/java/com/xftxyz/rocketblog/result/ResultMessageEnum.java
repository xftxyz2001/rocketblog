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

    // 博客错误2xx
    SELF_OPERATION(200, "不能对自己进行此操作"),
    ALREADY_DONE(201, "已经执行过该操作"),

    // 图片错误3xx
    IMAGE_ERROR(300, "图片错误");

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