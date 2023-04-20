package com.xftxyz.rocketblog.component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xftxyz.rocketblog.parameter.ChatMessageBody;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.ChatService;
import com.xftxyz.rocketblog.service.UserService;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 服务端
 */
@Slf4j
@Component
@ServerEndpoint(value = "/message/{token}")
public class MessageServer {

    // 用户连接集合
    private static Map<Long, Session> onlineMap = new ConcurrentHashMap<>();

    private static UserService userService;

    private static ChatService chatService;

    private static ObjectMapper objectMapper;

    @Autowired
    public void setUserService(UserService userService) {
        MessageServer.userService = userService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        MessageServer.chatService = chatService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        MessageServer.objectMapper = objectMapper;
    }

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
    public void onMessage(String sChatMessageBody, Session session, @PathParam("token") String token) {
        User user = userService.fromToken(token);
        if (user == null) {
            return;
        }
        // 解析出指定用户
        ChatMessageBody chatMsg = objectMapper.convertValue(sChatMessageBody, ChatMessageBody.class);
        // JSONObject jsonObj = JSONUtil.parseObj(sChatMessageBody);
        // ChatMessageBody chatMsg = jsonObj.toBean(ChatMessageBody.class);
        chat(user, chatMsg);

    }

    // 通知
    public void notice(Long to, String content) {
        // 获取指定用户的连接
        Session toSession = onlineMap.get(to);
        // 发送推送
        sendMessage("通知：" + content, toSession);
    }

    // 广播
    public void sendAll(String message) {
        for (Session session : onlineMap.values()) {
            sendMessage("系统：" + message, session);
        }
    }

    public void chat(User user, ChatMessageBody chatMessageBody) {
        Long from = user.getUserid();
        Long to = chatMessageBody.getTo();
        String content = chatMessageBody.getContent();

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
        log.warn("连接异常：{}", error);
    }

}
