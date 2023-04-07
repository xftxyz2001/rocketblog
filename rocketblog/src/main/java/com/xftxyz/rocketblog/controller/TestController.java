package com.xftxyz.rocketblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

    // 除零
    @GetMapping("/by0")
    public Double by0(@RequestParam("num") Double num) {
        return num / 0;
    }

    // 空返回
    @GetMapping("/null")
    public void nullReturn() {
    }

    // 发送消息
    @GetMapping("/chat/{to}/{msg}")
    public String testSendMessage(@PathVariable("to") Long toUserid, @PathVariable("msg") String content,
            HttpSession session) {
        User user = (User) Utils.currentUser(session);
        userService.chat(user.getUserid(), toUserid, content);
        return "消息发送成功";
    }
}
