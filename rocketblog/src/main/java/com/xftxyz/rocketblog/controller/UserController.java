package com.xftxyz.rocketblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;
import com.xftxyz.rocketblog.service.EmailService;
import com.xftxyz.rocketblog.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
// @CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    // 获取用户列表
    // @GetMapping("/users")
    // @CrossOrigin(origins = "*")
    // public List<User> getUsers() {
    // List<User> selectByExample = userMapper.selectByExample(null);
    // return selectByExample;

    // }
    // 获取验证码，存放到Session中，并通过邮件发送给用户
    @GetMapping("/code/{email}")
    public String code(HttpSession session, @PathVariable("email") String email) {
        // 随机生成六位数验证码
        String code = "" + (int) ((Math.random() * 9 + 1) * 100000);
        session.setAttribute("code", code);
        // session.getServletContext().setAttribute("code", code);

        // 发送邮件
        log.info(email + "的验证码为：" + code);
        emailService.sendSimpleMail(email, "火箭博客验证码", "您的验证码为：" + code);
        return "success";
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        String name = (String) requestBody.get("name");
        String password = (String) requestBody.get("password");
        String email = (String) requestBody.get("email");
        String vertify = (String) requestBody.get("vertify");
        // 获取用户输入的验证码
        String acode = (String) session.getAttribute("code");
        // 输出一下接收到的参数
        // log.info("name:" + name + ",password:" + password + ",email:" + email +
        // ",vertify:" + vertify);
        // log.info(session.getId());
        // String acode = (String) session.getServletContext().getAttribute("code");
        log.info("Session中的验证码：" + acode + "，用户输入的验证码：" + vertify);
        if (acode == null || !acode.equals(vertify)) {
            return "验证码错误";
        }
        // 注册
        String register = userService.register(name, password, email);
        return register;
    }

    // 登录
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, Object> requestBody,
            HttpSession session) {
        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");
        User user = userService.login(email, password);
        if (user == null) {
            // return "用户名或密码错误";
            return Result.fail(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        log.info("userid:" + user.getUserid() + ",username:" + user.getUsername() + ",email:" + user.getEmail() +
                ",avatar:" + user.getAvatar() + ",phone:" + user.getPhone());

        session.setAttribute("user", user);
        String username = user.getUsername();
        String avatar = user.getAvatar();
        Map<String, Object> map = Map.of("username", username, "avatar", avatar);
        log.info("map" + map.toString());
        return Result.success(map);
    }

    // 登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "success";
    }

    // 获取用户信息
    @GetMapping("/info")
    public User info(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user;
    }

}
