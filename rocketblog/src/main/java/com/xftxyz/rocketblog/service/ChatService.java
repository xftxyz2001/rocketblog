package com.xftxyz.rocketblog.service;

import java.util.List;

import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VChat;

public interface ChatService {
    // 发送消息
    public Integer chat(Long fromUserid, Long toUserid, String content);

    // 获取消息列表
    public List<VChat> getChats(Long userid);

    public Integer deleteChat(Long chatid);

    public List<ChatInfo> getSessionList(User user);

    public ChatInfo getSession(User user, Long userid);

    public List<VChat> getChatDetail(User user, Long userid);

    public Integer deleteSession(User user, Long userid);
}
