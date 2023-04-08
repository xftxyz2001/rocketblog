package com.xftxyz.rocketblog.pojo;

import java.util.Date;

public class ChatInfo {
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
    /**
     * 未读消息数量
     */
    private Integer msgNum;
    /**
     * 最后一条消息
     */
    private String lastMsg;
    /**
     * 最后一条消息的发送时间
     */
    private Date lastTime;

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

    public Integer getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(Integer msgNum) {
        this.msgNum = msgNum;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

}
