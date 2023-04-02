package com.xftxyz.rocketblog.result;

public enum ResultCode {
    // 非法操作
    ILLEGAL_OPERATION("100", "非法操作"),
    // 成功
    SUCCESS("200", "成功"),
    // 验证码错误
    CAPTCHA_ERROR("400", "验证码错误"),

    // 用户名已存在
    USERNAME_EXIST("411", "用户名已存在"),
    // 用户名不存在
    USER_NOT_EXIST("412", "用户不存在"),
    // 用户名或密码错误
    USERNAME_OR_PASSWORD_ERROR("413", "用户名或密码错误"),
    // 密码错误
    PASSWORD_ERROR("414", "密码错误"),

    // 用户未登录
    USER_NOT_LOGIN("444", "用户未登录"),
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
