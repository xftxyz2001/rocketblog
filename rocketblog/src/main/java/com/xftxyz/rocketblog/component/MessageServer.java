package com.xftxyz.rocketblog.component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xftxyz.rocketblog.parameter.ChatMessageBody;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.ChatService;
import com.xftxyz.rocketblog.service.UserService;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

/**
 * <b style="color: blue"> 自己给指定用户发送消息 </b>
 * 
 */
@Slf4j
@Component
@ServerEndpoint(value = "/message/{token}")
public class MessageServer {

    private static UserService userService;

    private static ChatService chatService;

    @Autowired
    public void setUserService(UserService userService) {
        MessageServer.userService = userService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        MessageServer.chatService = chatService;
    }

    // 用户连接集合
    private static Map<Long, Session> onlineMap = new ConcurrentHashMap<>();

    // 建立连接
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        User user = userService.fromToken(token);
        if (user == null) {
            return;
        }
        // 获取用户id
        Long userid = user.getUserid();
        // 将连接放入集合
        onlineMap.put(userid, session);
        // 验证成功
        sendMessage("身份验证成功", session);

    }

    // 关闭连接
    @OnClose
    public void onClose(Session session, @PathParam("token") String token) {
        User user = userService.fromToken(token.toString());
        if (user == null) {
            return;
        }
        // 获取用户id
        Long userid = user.getUserid();
        // 移除关闭的客户端连接
        onlineMap.remove(userid);
    }

    // 接收消息
    @OnMessage
    public void onMessage(String chatMessageBody, Session session, @PathParam("token") String token) {
        User user = userService.fromToken(token);
        if (user == null) {
            return;
        }
        // 解析出指定用户
        JSONObject jsonObj = JSONUtil.parseObj(chatMessageBody);
        ChatMessageBody chatMsg = jsonObj.toBean(ChatMessageBody.class);

        Long from = user.getUserid();
        Long to = chatMsg.getTo();
        String content = chatMsg.getContent();

        chatService.chat(from, to, content);

        // 获取指定用户的连接
        Session toSession = onlineMap.get(to);
        // 发送推送
        sendMessage(user.getUsername() + "：" + content, toSession);
    }

    // 发送消息
    private void sendMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            onError(session, e);
        }
    }

    // 连接异常
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("连接异常：{}", error);
    }

}
