package com.xftxyz.rocketblog.pojo;

public class UserBase {
    /**
     * 用户id
     */
    private Long userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像
     */
    private String avatar;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
