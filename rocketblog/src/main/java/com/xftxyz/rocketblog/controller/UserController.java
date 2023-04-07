package com.xftxyz.rocketblog.controller;

import java.io.IOException;
import java.util.List;

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
import com.xftxyz.rocketblog.exception.user.CaptchaErrorException;
import com.xftxyz.rocketblog.exception.user.PasswordErrorException;
import com.xftxyz.rocketblog.exception.user.UserNotExistException;
import com.xftxyz.rocketblog.parameter.ChatMessageBody;
import com.xftxyz.rocketblog.parameter.RegisterBody;
import com.xftxyz.rocketblog.parameter.ResetPasswordBody;
import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.pojo.VChat;
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

    /**
     * 用于发送包含用户密码的邮件到用户的邮箱，以帮助他们找回密码。
     * 
     * @param email 需要重置密码的用户的邮箱地址
     * @return 返回一个字符串，表示密码邮件已经成功发送到用户的邮箱
     * @throws UserNotExistException 如果提供的邮箱地址对应的用户不存在，则会抛出 UserNotExistException 异常
     */
    @GetMapping("/forget/{email}")
    public String forgetPassword(@PathVariable("email") String email) {
        // 获取与提供的电子邮件地址相应的用户
        User user = userService.getUserByEmail(email);
        // 如果找不到与该电子邮件地址相应的用户，则抛出 UserNotExistException 异常
        if (user == null) {
            throw new UserNotExistException(email);
        }
        // 否则，发送包含用户密码的邮件到用户的邮箱
        String content = EnvironmentVariables.EMAIL_FORGET_CONTENT + user.getPassword();
        emailService.sendSimpleMail(email, EnvironmentVariables.EMAIL_FORGET_SUBJECT, content);
        // 返回一个消息，指示密码邮件已发送到用户的邮箱
        return "密码邮件已发送到您的邮箱，请注意查收。";
    }

    /**
     * 用于生成并发送验证码到用户的邮箱。
     * 
     * @param session HttpSession对象，用于存储验证码
     * @param email   需要接收验证码的用户的邮箱地址
     * @return 返回一个字符串，表示验证码已经成功发送到用户的邮箱
     */
    @GetMapping("/code/{email}")
    public String getVerificationCode(HttpSession session, @PathVariable("email") String email) {
        // 随机生成六位数验证码
        String code = Utils.getRandomString(EnvironmentVariables.CODE_LENGTH);
        session.setAttribute(EnvironmentVariables.SESSION_CODE, code);

        // 发送邮件
        String content = EnvironmentVariables.EMAIL_CODE_CONTENT + code;
        emailService.sendSimpleMail(email, EnvironmentVariables.EMAIL_CODE_SUBJECT, content);

        // 返回一个消息，指示验证码已经成功发送到用户的邮箱
        return "验证码已发送到您的邮箱，请注意查收。";
    }

    /**
     * 注册新用户
     * 
     * @param registerBody 包含用户注册信息的 {@link RegisterBody} 对象
     * @param session      HttpSession对象，用于识别用户、获取验证码
     * @return 返回一个字符串，表示用户注册已成功
     * @throws CaptchaErrorException 如果提供的验证码与发送给用户的验证码不匹配，则会抛出
     *                               CaptchaErrorException 异常
     */
    @PostMapping("/register")
    public String register(@RequestBody RegisterBody registerBody, HttpSession session) throws CaptchaErrorException {
        String name = registerBody.getName();
        String password = registerBody.getPassword();
        String email = registerBody.getEmail();
        String vertify = registerBody.getVertify();

        // 获取发送给用户的验证码
        String acode = (String) session.getAttribute(EnvironmentVariables.SESSION_CODE);

        // 如果验证码为空或者与提供的验证码不匹配，则抛出 CaptchaErrorException 异常
        if (acode == null || !acode.equals(vertify)) {
            throw new CaptchaErrorException();
        }

        // 否则，注册用户
        userService.register(name, password, email);

        // 返回一个消息，指示用户注册已成功
        return "注册成功";
    }

    /**
     * 用于用户登录。
     * 
     * @param registerBody 包含用户登录信息的 {@link RegisterBody} 对象
     * @param session      HttpSession对象，用于存储用户信息
     * @param response     HttpServletResponse对象，用于设置Cookie
     * @return 返回一个 {@link UserInfo} 对象，包含用户信息
     */
    @PostMapping("/login")
    public UserInfo login(@RequestBody RegisterBody registerBody, HttpSession session, HttpServletResponse response) {
        // 解析请求体中的用户登录信息
        String email = registerBody.getEmail();
        String password = registerBody.getPassword();

        // 登录并获取用户信息
        User user = userService.login(email, password);

        // 将用户信息存放到Session中
        session.setAttribute(EnvironmentVariables.SESSION_USER, user);

        // 将用户信息存放到Cookie中
        Cookie cookie = new Cookie(EnvironmentVariables.COOKIE_TOKEN, userService.toToken(user));
        cookie.setPath("/");
        cookie.setMaxAge(EnvironmentVariables.COOKIE_TOKEN_EXPIRE);
        response.addCookie(cookie);

        // 获取用户信息并返回
        UserInfo userInfo = userService.getUserInfo(user);
        return userInfo;
    }

    /**
     * 用于用户登出。
     * 
     * @param session  HttpSession对象，用于删除用户登录信息
     * @param response HttpServletResponse对象，用于删除Cookie
     * @return 返回一个字符串，表示用户已经成功登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        // 使会话无效，并删除Cookie
        session.invalidate();
        Cookie cookie = new Cookie(EnvironmentVariables.COOKIE_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        // 返回一个消息，指示用户已经成功登出
        return "登出成功";
    }

    /**
     * 用于获取当前登录用户的信息。
     * 
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @return 返回一个 {@link UserInfo} 对象，包含当前登录用户的信息
     */
    @GetMapping("/i")
    public UserInfo getMyUserInfo(HttpSession session) {
        // 获取当前登录用户信息，并获取用户信息
        User user = Utils.currentUser(session);
        UserInfo userInfo = userService.getUserInfo(user);

        // 返回用户信息
        return userInfo;
    }

    /**
     * 用于获取当前登录用户的详细信息。
     * 
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @return 返回一个 {@link User} 对象，包含当前登录用户的详细信息，但密码将被屏蔽
     * @throws ClassNotFoundException 如果发生序列化错误，则会抛出 ClassNotFoundException 异常
     * @throws IOException            如果发生IO错误，则会抛出 IOException 异常
     */
    @GetMapping("/i/detail")
    public User getMyUserDetails(HttpSession session)
            throws UserNotExistException, ClassNotFoundException, IOException {
        // 获取当前登录用户信息
        User user = Utils.currentUser(session);

        // 复制用户信息，并将密码屏蔽
        User userCopy = user.deepClone();
        userCopy.setPassword(EnvironmentVariables.PASSWORD_MASK);

        // 返回用户信息
        return userCopy;
    }

    /**
     * 用于修改当前登录用户的信息。
     * 
     * @param newUserData 包含新用户信息的 {@link User} 对象
     * @param session     HttpSession对象，用于获取当前登录用户信息
     * @return 返回一个字符串，表示用户信息已经成功修改
     */
    @PostMapping("/update")
    public String updateUserInfo(@RequestBody User newUserData, HttpSession session) {
        // 获取当前登录用户信息，并更新用户信息
        User user = Utils.currentUser(session);
        updateAssign(user, newUserData);
        userService.updateUser(user);

        // 返回一个消息，指示用户信息已经成功修改
        return "用户信息修改成功";
    }

    /**
     * 为要更改的用户信息赋值
     * 
     * @param user        要被更改的用户对象
     * @param newUserData 新的用户信息
     */
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

    /**
     * 用于修改当前登录用户的邮箱。
     * 
     * @param registerBody 包含新邮箱信息和验证码的 {@link RegisterBody} 对象
     * @param session      HttpSession对象，用于获取当前登录用户信息和验证码
     * @return 返回一个字符串，表示用户邮箱已经成功修改
     * @throws CaptchaErrorException 如果提供的验证码与发送给用户的验证码不匹配，则会抛出
     *                               CaptchaErrorException 异常
     */
    @PostMapping("/update/email")
    public String updateEmail(@RequestBody RegisterBody registerBody, HttpSession session)
            throws CaptchaErrorException {
        // 获取当前登录用户信息
        User user = Utils.currentUser(session);

        // 解析请求体中的新邮箱信息和验证码
        String email = registerBody.getEmail();
        String vertify = registerBody.getVertify();

        // 获取发送给用户的验证码，并进行比较，如果不匹配则抛出 CaptchaErrorException 异常
        String acode = (String) session.getAttribute(EnvironmentVariables.SESSION_CODE);
        if (acode == null || !acode.equals(vertify)) {
            throw new CaptchaErrorException();
        }

        // 更新用户邮箱并保存到数据库中
        user.setEmail(email);
        userService.updateUser(user);

        // 返回一个消息，指示用户邮箱已经成功修改
        return "邮箱修改成功";
    }

    /**
     * 用于修改当前登录用户的密码。
     * 
     * @param resetPasswordBody 包含新密码和原密码的 {@link ResetPasswordBody} 对象
     * @param session           HttpSession对象，用于获取当前登录用户信息
     * @return 返回一个字符串，表示用户密码已经成功修改
     * @throws PasswordErrorException 如果原密码不正确，则会抛出 PasswordErrorException 异常
     */
    @PostMapping("/update/password")
    public String updatePassword(@RequestBody ResetPasswordBody resetPasswordBody, HttpSession session)
            throws PasswordErrorException {
        // 获取当前登录用户信息
        User user = Utils.currentUser(session);

        // 解析请求体中的新密码和原密码
        String password = resetPasswordBody.getPassword();
        String newPassword = resetPasswordBody.getNewPassword();

        // 如果原密码不正确，则抛出 PasswordErrorException 异常
        if (!user.getPassword().equals(password)) {
            throw new PasswordErrorException();
        }

        // 更新用户密码并保存到数据库中
        user.setPassword(newPassword);
        userService.updateUser(user);

        // 返回一个消息，指示用户密码已经成功修改
        return "密码修改成功";
    }

    /**
     * 用于获取指定 userid 的用户信息。
     * 
     * @param session HttpSession 对象，用于获取当前登录用户信息
     * @param userid  需要查询的用户的id
     * @return 返回一个 {@link UserInfo} 对象，包含指定 userid 的用户信息
     */
    @GetMapping("/info/{userid}")
    public UserInfo getUserInformation(HttpSession session, @PathVariable("userid") Long userid) {
        // 获取当前登录用户信息，并获取指定 userid 的用户信息
        User user = Utils.currentUser(session);
        UserInfo userInfo = userService.getUserInfo(user, userid);

        // 返回指定 userid 的用户信息
        return userInfo;
    }

    /**
     * 用于删除当前登录用户的账户。
     * 
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @return 返回一个字符串，表示用户账户已经成功删除
     */
    @DeleteMapping("/delete")
    public String deleteUser(HttpSession session) {
        // 获取当前登录用户信息，并删除用户账户
        User user = Utils.currentUser(session);
        userService.deleteUser(user.getUserid());

        // 注销并返回一个消息，指示用户账户已经成功删除
        session.invalidate();
        return "注销成功";
    }

    /**
     * 用于让当前登录用户关注指定的用户。
     * 
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @param userid  需要关注的用户的id
     * @return 返回一个 Long 类型的值，表示指定用户的最新粉丝数
     */
    @GetMapping("/follow/{userid}")
    public Long follow(HttpSession session, @PathVariable("userid") Long userid) {
        // 获取当前登录用户信息，并让该用户关注指定的用户
        User user = Utils.currentUser(session);
        Long newFansCount = userService.follow(user.getUserid(), userid);

        // 返回指定用户的最新粉丝数
        return newFansCount;
    }

    /**
     * 用于取消当前登录用户对指定用户的关注。
     * 
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @param userid  需要取消关注的用户的id
     * @return 返回一个 Long 类型的值，表示指定用户的最新粉丝数
     */
    @DeleteMapping("/follow/{userid}")
    public Long cancelFollow(HttpSession session, @PathVariable("userid") Long userid) {
        // 获取当前登录用户信息，并取消该用户对指定用户的关注
        User user = Utils.currentUser(session);
        Long newFansCount = userService.cancelFollow(user.getUserid(), userid);

        // 返回指定用户的最新粉丝数
        return newFansCount;
    }

    /**
     * 用于获取当前登录用户关注的用户列表。
     * 
     * @param session  HttpSession对象，用于获取当前登录用户信息
     * @param pageNum  获取的页面数，从1开始
     * @param pageSize 每页显示的记录数
     * @return 返回一个 {@link PageInfo} 对象，其中包含了指定页面的 {@link UserBase} 列表
     */
    @GetMapping("/followings")
    public PageInfo<UserBase> followings(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        // 获取当前登录用户信息，并获取该用户关注的用户列表
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<UserBase> followings = userService.getFollowings(user.getUserid());

        // 返回一个 PageInfo 对象，其中包含了指定页面的 UserBase 列表
        return new PageInfo<>(followings);
    }

    /**
     * 用于获取当前登录用户的粉丝列表。
     * 
     * @param session  HttpSession对象，用于获取当前登录用户信息
     * @param pageNum  获取的页面数，从1开始
     * @param pageSize 每页显示的记录数
     * @return 返回一个 {@link PageInfo} 对象，其中包含了指定页面的 {@link UserBase} 列表
     */
    @GetMapping("/followers")
    public PageInfo<UserBase> followers(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        // 获取当前登录用户信息，并获取该用户的粉丝列表
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<UserBase> followers = userService.getFollowers(user.getUserid());

        // 返回一个 PageInfo 对象，其中包含了指定页面的 UserBase 列表
        return new PageInfo<>(followers);
    }

    /**
     * 用于向指定的用户发送消息。
     * 
     * @param session     HttpSession对象，用于获取当前登录用户信息
     * @param chatMessage 包含了要发送的消息内容和目标用户ID的 {@link ChatMessageBody} 对象
     * @return 返回一个字符串，表示消息发送已成功
     */
    @PostMapping("/chat")
    public String sendMessage(HttpSession session, @RequestBody ChatMessageBody chatMessage) {
        // 获取当前登录用户信息，并获取要发送的消息内容和目标用户ID
        User user = Utils.currentUser(session);
        Long toUserid = chatMessage.getTo();
        String content = chatMessage.getContent();

        // 向指定的用户发送消息
        userService.chat(user.getUserid(), toUserid, content);

        // 返回一个消息，表示消息发送已成功
        return "消息发送成功";
    }

    /**
     * 用于获取当前登录用户的所有消息。
     * 
     * @param session  HttpSession对象，用于获取当前登录用户信息
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
        List<VChat> chats = userService.getChats(user.getUserid());

        // 返回一个 PageInfo 对象，其中包含了指定页面的 VChat 列表
        return new PageInfo<>(chats);
    }

    /**
     * 用于获取当前登录用户的所有会话列表。
     * 
     * @param session  HttpSession对象，用于获取当前登录用户信息
     * @param pageNum  获取的页面数，从1开始
     * @param pageSize 每页显示的记录数
     * @return 返回一个 {@link PageInfo} 对象，其中包含了指定页面的 {@link ChatInfo} 列表
     */
    @GetMapping("/chat/sessions")
    public PageInfo<ChatInfo> getChatSessionList(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        // 获取当前登录用户信息，并获取该用户的所有会话列表
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<ChatInfo> chatlist = userService.getSessionList(user);

        // 返回一个 PageInfo 对象，其中包含了指定页面的 ChatInfo 列表
        return new PageInfo<>(chatlist);
    }

    /**
     * 用于获取指定的会话信息。
     * 
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @param userid  要更新会话的目标用户ID
     * @return 返回一个 {@link ChatInfo} 对象，表示指定会话的详细信息
     */
    @GetMapping("/chat/session/{userid}")
    public ChatInfo updateChatSession(HttpSession session, @PathVariable("userid") Long userid) {
        // 获取当前登录用户信息，并获取指定会话的详细信息
        User user = Utils.currentUser(session);
        ChatInfo chat = userService.getSession(user, userid);

        // 返回一个 ChatInfo 对象，表示指定会话的详细信息
        return chat;
    }

    /**
     * 获取消息信息详情（对话）
     *
     * @param session  HttpSession对象，用于识别用户
     * @param userid   对话的用户ID
     * @param pageNum  分页查询的页码，默认值为1
     * @param pageSize 分页查询的每页大小，默认值为5
     * @return 返回一个 PageInfo 对象，其中包含了指定会话的每天对话信息 {@link VChat} 列表
     */
    @GetMapping("/chat/detail/{userid}")
    public PageInfo<VChat> chatDetail(HttpSession session, @PathVariable("userid") Long userid,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        User user = Utils.currentUser(session);
        List<VChat> chatDetail = userService.getChatDetail(user, userid);
        return new PageInfo<>(chatDetail);
    }

    /**
     * 删除指定的单条消息
     * 
     * @param session HttpSession对象，用于获取当前用户
     * @param chatid  要删除的消息ID
     * @return 返回一个字符串，表示消息删除成功
     */
    @DeleteMapping("/chat/{chatid}")
    public String deleteChat(HttpSession session, @PathVariable("chatid") Long chatid) {
        userService.deleteChat(chatid);
        return "消息删除成功";
    }

    /**
     * 删除指定的会话
     * 
     * @param session HttpSession对象，用于获取当前用户
     * @param userid  要删除会话的目标用户ID
     * @return 返回一个字符串，表示会话删除成功
     */
    @DeleteMapping("/chat/session/{userid}")
    public String deleteSession(HttpSession session, @PathVariable("userid") Long userid) {
        User user = Utils.currentUser(session);
        userService.deleteSession(user, userid);
        return "会话删除成功";
    }

}
