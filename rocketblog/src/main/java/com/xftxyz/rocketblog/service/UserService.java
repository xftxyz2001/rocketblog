package com.xftxyz.rocketblog.service;

import java.util.List;

import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserInfo;

public interface UserService {

    // 生成token
    public String toToken(String string, User user);

    // 解析token
    public User fromToken(String token);

    // 获取用户列表
    public List<UserInfo> getUserInfos();

    // 获取用户
    public User getUser(Long id);

    // 添加用户
    public Integer addUser(User user);

    // 删除用户
    public Integer deleteUser(Long id);

    // 更新用户
    public Integer updateUser(User user);

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
    public Long getFollowingCount(Long userid);

    public List<UserBase> getFollowings(Long userid);

    // 获取用户粉丝列表
    public Long getFollowerCount(Long userid);

    public List<UserBase> getFollowers(Long userid);

    // 获取用户发布的博客数
    public Long getBlogCount(Long userid);

    // QQ邮箱默认头像
    public String defaultAvatar(String email);

    public UserInfo getUserInfoById(Long id);

    public List<UserInfo> findUserInfosByExample(User user);

    public void checkCaptcha(String email, String vertify);

    public void changePassword(User user, String password, String newPassword);

    public boolean checkPassword(User user);

    public void deleteUserToken(Long userid);

    // public void deleteUserTokens(Long userid);

}
