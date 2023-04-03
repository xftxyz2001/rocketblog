package com.xftxyz.rocketblog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.VChat;
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

    // 获取用户详细信息
    @GetMapping("/info/detail")
    public Result<User> infoDetail(HttpSession session) throws ClassNotFoundException, IOException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        User userCopy = user.deepClone();
        userCopy.setPassword(null);
        log.info(((User) session.getAttribute("user")).getPassword());
        return Result.success(user);
    }

    // 修改用户信息
    @PostMapping("/update")
    public Result<Object> update(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }

        // 获取参数
        String username = (String) requestBody.get("username");
        String userSex = (String) requestBody.get("userSex");
        String phone = (String) requestBody.get("phone");
        String avatar = (String) requestBody.get("avatar");

        // 更新用户信息
        if (StringUtils.hasLength(username)) {
            user.setUsername(username);
        }
        if (StringUtils.hasLength(userSex)) {
            user.setUserSex(userSex);
        }
        if (StringUtils.hasLength(phone)) {
            user.setPhone(phone);
        }
        if (StringUtils.hasLength(avatar)) {
            user.setAvatar(avatar);
        }
        userService.updateUser(user);
        return Result.success();
    }

    // 修改邮箱
    @PostMapping("/update/email")
    public Result<Object> updateEmail(@RequestBody Map<String, Object> requestBody,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }

        // 获取参数
        String email = (String) requestBody.get("email");
        String vertify = (String) requestBody.get("vertify");
        // 获取用户输入的验证码
        String acode = (String) session.getAttribute("code");

        if (acode == null || !acode.equals(vertify)) {
            return Result.fail(ResultCode.CAPTCHA_ERROR);
        }
        // 更新邮箱
        user.setEmail(email);
        userService.updateUser(user);
        return Result.success();
    }

    // 修改密码
    @PostMapping("/update/password")
    public Result<Object> updatePassword(@RequestBody Map<String, Object> requestBody,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }

        // 获取参数
        String password = (String) requestBody.get("password");
        String newPassword = (String) requestBody.get("newPassword");

        // 如果原密码不正确
        if (!user.getPassword().equals(password)) {
            return Result.fail(ResultCode.PASSWORD_ERROR);
        }
        // 更新密码
        user.setPassword(newPassword);
        userService.updateUser(user);
        return Result.success();
    }

    @GetMapping("/info/{userid}")
    public Result<Map<String, Object>> info(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        // if (user == null) {
        // return Result.fail(ResultCode.USER_NOT_LOGIN);
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

    // 注销
    @DeleteMapping("/delete")
    public Result<Object> delete(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        userService.deleteUser(user.getUserid());
        session.invalidate();
        return Result.success();
    }

    // 忘记密码
    @PostMapping("/forget/{email}")
    public Result<Object> forget(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }
        // 发送邮件：用户的密码
        String content = "您的密码是：" + user.getPassword();
        emailService.sendSimpleMail(email, "找回密码", content);
        return Result.success();
    }

    // 关注
    @GetMapping("/follow/{userid}")
    public Result<Object> follow(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        Map<String, Object> follow = userService.follow(user.getUserid(), userid);
        return Result.custom((String) follow.get("msg"), follow.get("followers"));
    }

    // 取消关注
    @DeleteMapping("/follow/{userid}")
    public Result<Object> cancelFollow(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        Map<String, Object> follow = userService.cancelFollow(user.getUserid(), userid);
        return Result.custom((String) follow.get("msg"), follow.get("followers"));
    }

    // 获取用户关注列表
    @GetMapping("/followings")
    public Result<PageInfo<UserBase>> followings(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<UserBase> followings = userService.getFollowings(user.getUserid());
        PageInfo<UserBase> pageInfo = new PageInfo<>(followings);
        return Result.success(pageInfo);
    }

    // 获取用户粉丝列表
    @GetMapping("/followers")
    public Result<PageInfo<UserBase>> followers(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<UserBase> followers = userService.getFollowers(user.getUserid());
        PageInfo<UserBase> pageInfo = new PageInfo<>(followers);
        return Result.success(pageInfo);
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
        int chat = userService.chat(user.getUserid(), toUserid, content);
        return Result.success(chat);
    }

    // 获取所有消息
    @GetMapping("/chats")
    public Result<Object> chats(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<VChat> chats = userService.getChats(user.getUserid());
        PageInfo<VChat> pageInfo = new PageInfo<>(chats);
        return Result.success(pageInfo);
    }

    // 获取会话列表
    @GetMapping("/char/sessions")
    public Result<Object> chatlist(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ChatInfo> chatlist = userService.getSessionList(user);
        PageInfo<ChatInfo> pageInfo = new PageInfo<>(chatlist);
        return Result.success(pageInfo);
    }

    // 更新指定会话
    @GetMapping("/chat/session/{userid}")
    public Result<Object> chat(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        ChatInfo chat = userService.getSession(user, userid);
        return Result.success(chat);
    }

    // 获取消息信息详情（对话）
    @GetMapping("/chat/detail/{userid}")
    public Result<Object> chatDetail(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        List<VChat> chatDetail = userService.getChatDetail(user, userid);
        return Result.success(chatDetail);
    }

    // 删除单条消息
    @DeleteMapping("/chat/{chatid}")
    public Result<Object> deleteChat(HttpSession session, @PathVariable("chatid") Long chatid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        int deleteChat = userService.deleteChat(chatid);
        return Result.success(deleteChat);
    }

    // 删除会话
    @DeleteMapping("/chat/session/{userid}")
    public Result<Object> deleteSession(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        int deleteSession = userService.deleteSession(user, userid);
        return Result.success(deleteSession);
    }

}
