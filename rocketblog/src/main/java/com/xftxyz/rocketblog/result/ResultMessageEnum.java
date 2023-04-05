package com.xftxyz.rocketblog.result;

public enum ResultMessageEnum {
    // 成功
    SUCCESS(0, "成功"),
    // 失败
    FAIL(-1, "失败"),
    // 未知错误
    UNKNOWN_ERROR(-2, "未知错误"),

    // 非法操作
    ILLEGAL_OPERATION(100, "非法操作"),
    // 成功
    // 验证码错误
    CAPTCHA_ERROR(400, "验证码错误"),

    // 用户名已存在
    USERNAME_EXIST(411, "用户名已存在"),
    // 用户名不存在
    USER_NOT_EXIST(412, "用户不存在"),
    // 用户名或密码错误
    USERNAME_OR_PASSWORD_ERROR(413, "用户名或密码错误"),
    // 密码错误
    PASSWORD_ERROR(414, "密码错误"),

    // 用户未登录
    USER_NOT_LOGIN(444, "用户未登录"),
    // 服务器错误
    SERVER_ERROR(500, "服务器错误"),
    CUSTOM(999, "自定义内容"),
    IMAGE_ERROR(666, "图片错误");

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
