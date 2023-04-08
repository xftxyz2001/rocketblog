package com.xftxyz.rocketblog.parameter;

public class RegisterBody {
    /**
     * 用户名（昵称）
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String vertify;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVertify() {
        return vertify;
    }

    public void setVertify(String vertify) {
        this.vertify = vertify;
    }

}
