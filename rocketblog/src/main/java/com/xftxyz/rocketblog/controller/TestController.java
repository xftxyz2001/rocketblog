package com.xftxyz.rocketblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xftxyz.rocketblog.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            // @RequestParam("vertify") String vertify,
            HttpSession session) {
        // String name = (String) requestBody.get("name");
        // String password = (String) requestBody.get("password");
        // String email = (String) requestBody.get("email");
        // String vertify = (String) requestBody.get("vertify");
        // // 获取用户输入的验证码
        // String acode = session.getAttribute("code").toString();
        // 输出一下接收到的参数
        // log.info("name:" + name + ",password:" + password + ",email:" + email +
        // ",vertify:" + vertify);
        // log.info(session.getId());
        // String acode = (String) session.getServletContext().getAttribute("code");
        // log.info("Application中的验证码：" + acode);
        // if (acode == null || !acode.equals(vertify)) {
        // return "验证码错误";
        // }
        // 注册
        // String register = userService.register(name, password, email);
        // return register;
        return "success";
    }
}
