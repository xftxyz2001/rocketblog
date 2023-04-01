package com.xftxyz.rocketblog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    // 获取验证码，存放到Session中，并通过邮件发送给用户
    @GetMapping("/code/{email}")
    public Result<Object> code(HttpSession session, @PathVariable("email") String email) {
        // 随机生成六位数验证码
        String code = "" + (int) ((Math.random() * 9 + 1) * 100000);
        session.setAttribute("code", code);

        // 发送邮件
        log.info(session.getId() + ":" + email + ":" + code);
        emailService.sendSimpleMail(email, "火箭博客验证码", "您的验证码为：" + code);
        return Result.success();
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

        log.info(session.getId() + ":" + acode + ":" + vertify + ":" + name + ":" + password + ":" + email);
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
            return Result.fail(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }

        session.setAttribute("user", user);
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        return Result.success(map);
    }

    // 获取用户信息
    @GetMapping("/info")
    public Result<Map<String, Object>> info(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        Map<String, Object> userInfo = userService.getUserInfo(user);
        return Result.success(userInfo);
    }

    @GetMapping("/info/{userid}")
    public Result<Map<String, Object>> info(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        // if (user == null) {
        //     return Result.fail(ResultCode.USER_NOT_LOGIN);
        // }
        Map<String, Object> userInfo = userService.getUserInfo(user, userid);
        return Result.success(userInfo);
    }

    // 登出
    @GetMapping("/logout")
    public Result<Object> logout(HttpSession session) {
        session.invalidate();
        return Result.success();
    }

    // 关注
    @GetMapping("/follow/{userid}")
    public Result<Object> follow(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        userService.follow(user.getUserid(), userid);
        return Result.success();
    }

    // 取消关注
    @DeleteMapping("/follow/{userid}")
    public Result<Object> cancelFollow(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        userService.cancelFollow(user.getUserid(), userid);
        return Result.success();
    }

    // 获取用户关注列表
    @GetMapping("/followings")
    public Result<Map<String, Object>> followings(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        // TODO: 分页
        Map<String, Object> followings = userService.getFollowings(user.getUserid());
        return Result.success(followings);
    }

    // 获取用户粉丝列表
    @GetMapping("/followers")
    public Result<Map<String, Object>> followers(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        // TODO: 分页
        Map<String, Object> followers = userService.getFollowers(user.getUserid());
        return Result.success(followers);
    }

    // 发送消息
    @PostMapping("/chat")
    public Result<Object> chat(HttpSession session, @RequestBody Map<String, Object> requestBody) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        Long toUserid = Long.parseLong((String) requestBody.get("to"));
        String content = (String) requestBody.get("content");
        userService.chat(user.getUserid(), toUserid, content);
        return Result.success();
    }

}
