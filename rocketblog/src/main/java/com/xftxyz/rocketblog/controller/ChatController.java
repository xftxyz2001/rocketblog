package com.xftxyz.rocketblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xftxyz.rocketblog.parameter.ChatMessageBody;
import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VChat;
import com.xftxyz.rocketblog.service.ChatService;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.HttpSession;

/**
 * 私信相关
 */
@RestController
@RequestMapping("/user/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    /**
     * 向指定的用户发送消息。
     * 
     * @param session     HttpSession对象，获取当前登录用户信息
     * @param chatMessage 包含了要发送的消息内容和目标用户ID的 {@link ChatMessageBody} 对象
     * @return 返回一个字符串，表示消息发送已成功
     */
    @PostMapping("")
    public String sendMessage(HttpSession session, @RequestBody ChatMessageBody chatMessage) {
        // 获取当前登录用户信息，并获取要发送的消息内容和目标用户ID
        User user = Utils.currentUser(session);
        Long toUserid = chatMessage.getTo();
        String content = chatMessage.getContent();

        // 向指定的用户发送消息
        chatService.chat(user.getUserid(), toUserid, content);

        // 返回一个消息，表示消息发送已成功
        return "消息发送成功";
    }

    /**
     * 获取当前登录用户的所有消息。
     * 
     * @param session  HttpSession对象，获取当前登录用户信息
     * @param pageNum  获取的页面数，从1开始
     * @param pageSize 每页显示的记录数
     * @return 返回一个 {@link PageInfo} 对象，其中包含了指定页面的 {@link VChat} 列表
     */
    @GetMapping("/chats")
    public PageInfo<VChat> getAllMessages(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        // 获取当前登录用户信息，并获取该用户的所有消息
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<VChat> chats = chatService.getChats(user.getUserid());

        // 返回一个 PageInfo 对象，其中包含了指定页面的 VChat 列表
        return new PageInfo<>(chats);
    }

    /**
     * 获取当前登录用户的所有会话列表。
     * 
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，其中包含了指定页面的 {@link ChatInfo} 列表
     */
    @GetMapping("/sessions")
    public PageInfo<ChatInfo> getChatSessionList(HttpSession session) {
        // 获取当前登录用户信息，并获取该用户的所有会话列表
        User user = Utils.currentUser(session);
        List<ChatInfo> chatlist = chatService.getSessionList(user);

        // 返回一个 PageInfo 对象，其中包含了指定页面的 ChatInfo 列表
        return new PageInfo<>(chatlist);
    }

    /**
     * 获取指定的会话信息。
     * 
     * @param session HttpSession对象，获取当前登录用户信息
     * @param userid  要更新会话的目标用户ID
     * @return 返回一个 {@link ChatInfo} 对象，表示指定会话的详细信息
     */
    @GetMapping("/session/{userid}")
    public ChatInfo updateChatSession(HttpSession session, @PathVariable("userid") Long userid) {
        // 获取当前登录用户信息，并获取指定会话的详细信息
        User user = Utils.currentUser(session);
        ChatInfo chat = chatService.getSession(user, userid);

        // 返回一个 ChatInfo 对象，表示指定会话的详细信息
        return chat;
    }

    /**
     * 获取消息信息详情（对话）
     *
     * @param session  HttpSession对象，识别用户
     * @param userid   对话的用户ID
     * @param pageNum  分页查询的页码，默认值为1
     * @param pageSize 分页查询的每页大小，默认值为5
     * @return 返回一个 PageInfo 对象，其中包含了指定会话的每天对话信息 {@link VChat} 列表
     */
    @GetMapping("/detail/{userid}")
    public PageInfo<VChat> chatDetail(HttpSession session, @PathVariable("userid") Long userid,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        User user = Utils.currentUser(session);
        List<VChat> chatDetail = chatService.getChatDetail(user, userid);
        return new PageInfo<>(chatDetail);
    }

    /**
     * 删除指定的单条消息
     * 
     * @param session HttpSession对象，获取当前用户
     * @param chatid  要删除的消息ID
     * @return 返回一个字符串，表示消息删除成功
     */
    @DeleteMapping("/{chatid}")
    public String deleteChat(HttpSession session, @PathVariable("chatid") Long chatid) {
        chatService.deleteChat(chatid);
        return "消息删除成功";
    }

    /**
     * 删除指定的会话
     * 
     * @param session HttpSession对象，获取当前用户
     * @param userid  要删除会话的目标用户ID
     * @return 返回一个字符串，表示会话删除成功
     */
    @DeleteMapping("/session/{userid}")
    public String deleteSession(HttpSession session, @PathVariable("userid") Long userid) {
        User user = Utils.currentUser(session);
        chatService.deleteSession(user, userid);
        return "会话删除成功";
    }

}
