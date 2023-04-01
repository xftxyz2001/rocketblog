package com.xftxyz.rocketblog.result;

public enum ResultCode {
    // 成功
    SUCCESS("200", "成功"),
    // 验证码错误
    CAPTCHA_ERROR("400", "验证码错误"),
    // 用户名已存在
    USERNAME_EXIST("401", "用户名已存在"),
    // 用户名或密码错误
    USERNAME_OR_PASSWORD_ERROR("402", "用户名或密码错误"),

    // 用户未登录
    USER_NOT_LOGIN("403", "用户未登录"),
    // 服务器错误
    SERVER_ERROR("500", "服务器错误"),
    CUSTOM("999", "自定义内容");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
