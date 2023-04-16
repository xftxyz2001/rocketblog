package com.xftxyz.rocketblog.parameter;

public class UpdateUserBody {
    /**
     * 用户名
     */
    private String username;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
