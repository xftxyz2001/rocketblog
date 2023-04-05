package com.xftxyz.rocketblog.service;

import java.util.List;

import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.pojo.VChat;

public interface UserService {

    // 生成token
    public String toToken(User user);

    // 解析token
    public User fromToken(String token);

    // 获取用户列表
    public List<User> getUsers();

    // 获取用户
    public User getUser(Long id);

    // 添加用户
    public int addUser(User user);

    // 删除用户
    public int deleteUser(Long id);

    // 更新用户
    public int updateUser(User user);

    public List<User> getUsersLikeName(String name);

    public List<User> getUsersLikeEmail(String email);

    public User getUserByEmail(String email);

    public void register(String name, String password, String email);

    public User login(String email, String password);

    // 获取用户信息
    public UserInfo getUserInfo(User user);

    public UserInfo getUserInfo(User me, Long userid);

    public Long follow(Long userFollowing, Long userFollowed);

    public Long cancelFollow(Long userFollowing, Long userFollowed);

    // 获取用户关注列表
    public long getFollowingCount(Long userid);

    public List<UserBase> getFollowings(Long userid);

    // 获取用户粉丝列表
    public long getFollowerCount(Long userid);

    public List<UserBase> getFollowers(Long userid);

    // 获取用户发布的博客数
    public long getBlogCount(Long userid);

    // 发送消息
    public int chat(Long fromUserid, Long toUserid, String content);

    // 获取消息列表
    public List<VChat> getChats(Long userid);

    public int deleteChat(Long chatid);

    // QQ邮箱默认头像
    public String defaultAvatar(String email);

    public List<ChatInfo> getSessionList(User user);

    public ChatInfo getSession(User user, Long userid);

    public List<VChat> getChatDetail(User user, Long userid);

    public int deleteSession(User user, Long userid);

}
