package com.xftxyz.rocketblog.parameter;

public class RegisterBody {
    private String name;
    private String password;
    private String email;
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
