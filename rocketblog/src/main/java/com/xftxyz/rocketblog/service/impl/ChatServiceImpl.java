package com.xftxyz.rocketblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.exception.chat.NoChatException;
import com.xftxyz.rocketblog.exception.user.UserNotExistException;
import com.xftxyz.rocketblog.mapper.ChatMapper;
import com.xftxyz.rocketblog.mapper.UserMapper;
import com.xftxyz.rocketblog.mapper.VChatMapper;
import com.xftxyz.rocketblog.pojo.Chat;
import com.xftxyz.rocketblog.pojo.ChatExample;
import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VChat;
import com.xftxyz.rocketblog.pojo.VChatExample;
import com.xftxyz.rocketblog.service.ChatService;
import com.xftxyz.rocketblog.status.ReadStatus;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    VChatMapper vChatMapper;

    @Override
    public List<ChatInfo> getSessionList(User user) {
        List<VChat> chats = getChats(user.getUserid());
        Map<Long, List<VChat>> map = new HashMap<>();
        for (VChat chat : chats) {
            // 获取对方的userid
            Long userid = chat.getUseridFrom().equals(user.getUserid()) ? chat.getUseridTo() : chat.getUseridFrom();
            if (map.containsKey(userid)) {
                map.get(userid).add(chat);
            } else {
                List<VChat> list = new ArrayList<>();
                list.add(chat);
                map.put(userid, list);
            }
        }
        List<ChatInfo> chatInfos = new ArrayList<>();
        for (Long userid : map.keySet()) {
            chatInfos.add(createChatInfo(userid, map.get(userid)));
        }
        chatInfos.sort((c1, c2) -> c2.getLastTime().compareTo(c1.getLastTime()));
        return chatInfos;
    }

    @Override
    public ChatInfo getSession(User user, Long userid) {
        if (userMapper.selectByPrimaryKey(userid) == null) {
            throw new UserNotExistException(String.valueOf(userid));
        }
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridFromEqualTo(user.getUserid()).andUseridToEqualTo(userid); // 我发给对方的
        exChats.or().andUseridFromEqualTo(userid).andUseridToEqualTo(user.getUserid()); // 对方发给我的
        exChats.setOrderByClause("createtime desc");
        List<VChat> chats = vChatMapper.selectByExample(exChats);
        if (chats.size() < 1) {
            throw new NoChatException(String.valueOf(userid));
        }
        return createChatInfo(userid, chats);
    }

    // userid：别人
    private ChatInfo createChatInfo(Long userid, List<VChat> chats) {
        if (chats.size() < 0) {
            return null;
        }
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setUserid(userid); // 对方的userid
        VChat lastChat = chats.get(0);
        Long useridFrom = lastChat.getUseridFrom();
        chatInfo.setUsername(useridFrom == userid ? lastChat.getFromUsername() : lastChat.getToUsername());
        chatInfo.setAvatar(useridFrom == userid ? lastChat.getFromAvatar() : lastChat.getToAvatar());
        chatInfo.setLastMsg(lastChat.getMessageContent());
        chatInfo.setLastTime(lastChat.getCreatetime());
        int count = 0;
        for (VChat chat : chats) {
            // from是别人，且未读
            if (chat.getUseridFrom() == userid && chat.getReaded() == ReadStatus.UNREAD) {
                count++;
            }
        }
        chatInfo.setMsgNum(count);
        return chatInfo;
    }

    @Override
    public Integer chat(Long fromUserid, Long toUserid, String content) {

        if (userMapper.selectByPrimaryKey(toUserid) == null) {
            throw new UserNotExistException(String.valueOf(toUserid));
        }
        // if (StringUtils.hasLength(content) && content.length() > EnvironmentVariables.MAX_LENGTH) {
        //     throw new IllegalArgumentException("内容长度不能超过" + EnvironmentVariables.MAX_LENGTH + "个字符");
        // }
        Chat chat = new Chat();
        chat.setUseridFrom(fromUserid);
        chat.setUseridTo(toUserid);
        chat.setMessageContent(content);
        chat.setCreatetime(new Date());
        chat.setReaded(ReadStatus.UNREAD);
        int insert = chatMapper.insert(chat);
        return insert;
    }

    @Override
    public List<VChat> getChats(Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridToEqualTo(userid); // 发给我的
        exChats.or().andUseridFromEqualTo(userid); // 我发出的
        exChats.setOrderByClause("createtime desc");
        List<VChat> chats = vChatMapper.selectByExample(exChats);
        return chats;
    }

    @Override
    public Integer deleteChat(Long chatid) {
        int delete = chatMapper.deleteByPrimaryKey(chatid);
        return delete;
    }

    @Override
    public List<VChat> getChatDetail(User user, Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridFromEqualTo(user.getUserid()).andUseridToEqualTo(userid); // 我发给对方的
        exChats.or().andUseridFromEqualTo(userid).andUseridToEqualTo(user.getUserid()); // 对方发给我的
        exChats.setOrderByClause("createtime desc");
        List<VChat> chats = vChatMapper.selectByExample(exChats);
        // 更新为已读
        updateRead(chats, userid);
        return chats;
    }

    private void updateRead(List<VChat> chats, Long userid) {
        for (VChat vchat : chats) {
            if (vchat.getUseridFrom() == userid && vchat.getReaded() == ReadStatus.UNREAD) {
                Chat chat = new Chat();
                chat.setChatId(vchat.getChatId());
                chat.setReaded(ReadStatus.READ);
                chatMapper.updateByPrimaryKeySelective(chat);
            }
        }
    }

    @Override
    public Integer deleteSession(User user, Long userid) {
        ChatExample exChats = new ChatExample();
        exChats.createCriteria().andUseridFromEqualTo(user.getUserid()).andUseridToEqualTo(userid); // 我发给对方的
        exChats.or().andUseridFromEqualTo(userid).andUseridToEqualTo(user.getUserid()); // 对方发给我的
        int delete = chatMapper.deleteByExample(exChats);
        return delete;
    }

}
