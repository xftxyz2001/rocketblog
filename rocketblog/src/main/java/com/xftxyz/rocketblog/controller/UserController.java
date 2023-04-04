package com.xftxyz.rocketblog.controller;

import java.io.IOException;
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
import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.parameter.RegisterBody;
import com.xftxyz.rocketblog.parameter.ResetPasswordBody;
import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.pojo.VChat;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;
import com.xftxyz.rocketblog.service.EmailService;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    // 忘记密码
    @PostMapping("/forget/{email}")
    public Result<Object> forgetPassword(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }
        // 发送邮件：用户的密码
        String content = EnvironmentVariables.EMAIL_FORGET_CONTENT + user.getPassword();
        emailService.sendSimpleMail(email, EnvironmentVariables.EMAIL_FORGET_SUBJECT, content);
        return Result.success();
    }

    // 获取验证码，存放到Session中，并通过邮件发送给用户
    @GetMapping("/code/{email}")
    public Result<Object> getVerificationCode(HttpSession session, @PathVariable("email") String email) {
        // 随机生成六位数验证码
        String code = Utils.getRandomString(EnvironmentVariables.CODE_LENGTH);
        session.setAttribute(EnvironmentVariables.SESSION_CODE, code);

        // 发送邮件
        String content = EnvironmentVariables.EMAIL_CODE_CONTENT + code;
        emailService.sendSimpleMail(email, EnvironmentVariables.EMAIL_CODE_SUBJECT, content);
        return Result.success();
    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody RegisterBody registerBody, HttpSession session) {
        String name = registerBody.getName();
        String password = registerBody.getPassword();
        String email = registerBody.getEmail();
        String vertify = registerBody.getVertify();
        // 获取用户输入的验证码
        String acode = (String) session.getAttribute(EnvironmentVariables.SESSION_CODE);

        if (acode == null || !acode.equals(vertify)) {
            return "验证码错误";
        }
        // 注册
        String register = userService.register(name, password, email);
        return register;
    }

    // 登录
    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody Map<String, Object> requestBody,
            HttpSession session, HttpServletResponse response) {
        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");
        User user = userService.login(email, password);
        if (user == null) {
            return Result.fail(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }

        // 登录成功，将用户信息存放到Session中
        session.setAttribute(EnvironmentVariables.SESSION_USER, user);
        // 将用户信息存放到Cookie中
        Cookie cookie = new Cookie(EnvironmentVariables.COOKIE_TOKEN, userService.toToken(user));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7天
        response.addCookie(cookie);
        UserInfo userInfo = userService.getUserInfo(user);
        return Result.success(userInfo);
    }

    // 登出
    @GetMapping("/logout")
    public Result<Object> logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie(EnvironmentVariables.COOKIE_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return Result.success();
    }

    // 获取用户详细信息
    @GetMapping("/info/detail")
    public Result<User> getMyUserDetails(HttpSession session) throws ClassNotFoundException, IOException {
        User user = (User) Utils.currentUser(session);
        User userCopy = user.deepClone();
        userCopy.setPassword(EnvironmentVariables.PASSWORD_MASK);
        return Result.success(userCopy);
    }

    // 修改用户信息
    @PostMapping("/update")
    public Result<Object> updateUserInfo(@RequestBody User newUserData, HttpSession session) {
        User user = (User) Utils.currentUser(session);

        updateAssign(user, newUserData);
        userService.updateUser(user);
        return Result.success();
    }

    // 为要更改的用户信息赋值
    private void updateAssign(User user, User newUserData) {
        // 获取参数
        String username = newUserData.getUsername();
        String userSex = newUserData.getUserSex();
        String phone = newUserData.getPhone();
        String avatar = newUserData.getAvatar();

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
    }

    // 修改邮箱
    @PostMapping("/update/email")
    public Result<Object> updateEmail(@RequestBody RegisterBody registerBody,
            HttpSession session) {
        User user = (User) Utils.currentUser(session);

        // 获取参数
        String email = registerBody.getEmail();
        String vertify = registerBody.getVertify();
        // 获取用户输入的验证码
        String acode = (String) session.getAttribute(EnvironmentVariables.SESSION_CODE);

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
    public Result<Object> updatePassword(@RequestBody ResetPasswordBody resetPasswordBody,
            HttpSession session) {
        User user = (User) Utils.currentUser(session);

        // 获取参数
        String password = resetPasswordBody.getPassword();
        String newPassword = resetPasswordBody.getNewPassword();

        // 如果原密码不正确
        if (!user.getPassword().equals(password)) {
            return Result.fail(ResultCode.PASSWORD_ERROR);
        }
        // 更新密码
        user.setPassword(newPassword);
        userService.updateUser(user);
        return Result.success();
    }

    @GetMapping("/info")
    public Result<UserInfo> getMyUserInfo(HttpSession session) {
        User user = (User) Utils.currentUser(session);
        UserInfo userInfo = userService.getUserInfo(user);
        return Result.success(userInfo);
    }

    @GetMapping("/info/{userid}")
    public Result<UserInfo> getUserInformation(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) Utils.currentUser(session);
        UserInfo userInfo = null;
        if (userid == null) {
            userInfo = userService.getUserInfo(user);
        }
        userInfo = userService.getUserInfo(user, userid);
        return Result.success(userInfo);
    }

    // 注销
    @DeleteMapping("/delete")
    public Result<Object> deleteUser(HttpSession session) {
        User user = (User) Utils.currentUser(session);
        userService.deleteUser(user.getUserid());
        session.invalidate();
        return Result.success();
    }

    // 关注
    @GetMapping("/follow/{userid}")
    public Result<Object> follow(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) Utils.currentUser(session);
        Map<String, Object> follow = userService.follow(user.getUserid(), userid);
        return Result.custom((String) follow.get("msg"), follow.get("followers"));
    }

    // 取消关注
    @DeleteMapping("/follow/{userid}")
    public Result<Object> cancelFollow(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) Utils.currentUser(session);
        Map<String, Object> follow = userService.cancelFollow(user.getUserid(), userid);
        return Result.custom((String) follow.get("msg"), follow.get("followers"));
    }

    // 获取用户关注列表
    @GetMapping("/followings")
    public Result<PageInfo<UserBase>> followings(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<UserBase> followings = userService.getFollowings(user.getUserid());
        PageInfo<UserBase> pageInfo = new PageInfo<>(followings);
        return Result.success(pageInfo);
    }

    // 获取用户粉丝列表
    @GetMapping("/followers")
    public Result<PageInfo<UserBase>> followers(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<UserBase> followers = userService.getFollowers(user.getUserid());
        PageInfo<UserBase> pageInfo = new PageInfo<>(followers);
        return Result.success(pageInfo);
    }

    // 发送消息
    @PostMapping("/chat")
    public Result<Object> sendMessage(HttpSession session, @RequestBody Map<String, Object> requestBody) {
        User user = (User) Utils.currentUser(session);
        Long toUserid = Long.parseLong((String) requestBody.get("to"));
        String content = (String) requestBody.get("content");
        int chat = userService.chat(user.getUserid(), toUserid, content);
        return Result.success(chat);
    }

    // 获取所有消息
    @GetMapping("/chats")
    public Result<Object> getAllMessages(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<VChat> chats = userService.getChats(user.getUserid());
        PageInfo<VChat> pageInfo = new PageInfo<>(chats);
        return Result.success(pageInfo);
    }

    // 获取会话列表
    @GetMapping("/char/sessions")
    public Result<Object> getChatSessionList(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<ChatInfo> chatlist = userService.getSessionList(user);
        PageInfo<ChatInfo> pageInfo = new PageInfo<>(chatlist);
        return Result.success(pageInfo);
    }

    // 更新指定会话
    @GetMapping("/chat/session/{userid}")
    public Result<Object> updateChatSession(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) Utils.currentUser(session);
        ChatInfo chat = userService.getSession(user, userid);
        return Result.success(chat);
    }

    // 获取消息信息详情（对话）
    @GetMapping("/chat/detail/{userid}")
    public Result<Object> chatDetail(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) Utils.currentUser(session);
        List<VChat> chatDetail = userService.getChatDetail(user, userid);
        return Result.success(chatDetail);
    }

    // 删除单条消息
    @DeleteMapping("/chat/{chatid}")
    public Result<Object> deleteChat(HttpSession session, @PathVariable("chatid") Long chatid) {
        int deleteChat = userService.deleteChat(chatid);
        return Result.success(deleteChat);
    }

    // 删除会话
    @DeleteMapping("/chat/session/{userid}")
    public Result<Object> deleteSession(HttpSession session, @PathVariable("userid") Long userid) {
        User user = (User) Utils.currentUser(session);
        int deleteSession = userService.deleteSession(user, userid);
        return Result.success(deleteSession);
    }

}
