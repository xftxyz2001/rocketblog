package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.ChatMapper;
import com.xftxyz.rocketblog.mapper.FollowMapper;
import com.xftxyz.rocketblog.mapper.UserMapper;
import com.xftxyz.rocketblog.mapper.VChatMapper;
import com.xftxyz.rocketblog.pojo.BlogExample;
import com.xftxyz.rocketblog.pojo.Chat;
import com.xftxyz.rocketblog.pojo.Follow;
import com.xftxyz.rocketblog.pojo.FollowExample;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserExample;
import com.xftxyz.rocketblog.pojo.VChat;
import com.xftxyz.rocketblog.pojo.VChatExample;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.status.BlogStatus;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    FollowMapper followMapper;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    VChatMapper vChatMapper;

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.selectByExample(null);
        return users;
    }

    @Override
    public User getUser(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public int addUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public int deleteUser(Long id) {
        int delete = userMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public int updateUser(User user) {
        int update = userMapper.updateByPrimaryKey(user);
        return update;
    }

    @Override
    public List<User> getUsersLikeName(String name) {
        UserExample exName = new UserExample();
        exName.createCriteria().andUsernameLike("%" + name + "%");
        List<User> userList = userMapper.selectByExample(exName);
        return userList;
    }

    @Override
    public List<User> getUsersLikeEmail(String email) {
        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailLike("%" + email + "%");
        List<User> userList = userMapper.selectByExample(exEmail);
        return userList;
    }

    @Override
    public String register(String name, String password, String email) {

        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailEqualTo(email);
        long countEmail = userMapper.countByExample(exEmail);
        if (countEmail > 0) {
            return "邮箱已存在";
        }
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);

        // 默认头像
        user.setAvatar(defaultAvatar(email));

        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return "注册成功";
    }

    @Override
    public User login(String email, String password) {
        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailEqualTo(email).andPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(exEmail);
        if (userList.size() < 1) {
            return null;
        }
        User user = userList.get(0);
        // 更新最后登录时间
        user.setLastLogin(new Date());
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    // 创建userInfo
    private Map<String, Object> createUserInfo(User user) {
        // 用户名、头像
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("avatar", user.getAvatar());
        
        // 关注: userid_following -> userid_followed
        // 关注数: followings
        userInfo.put("followings", getFollowingCount(user.getUserid()));

        // 粉丝数: followers
        userInfo.put("followers", getFollowerCount(user.getUserid()));

        // 文章数: blogs
        userInfo.put("blogs", getBlogCount(user.getUserid()));
        return userInfo;
    }

    @Override
    public Map<String, Object> getUserInfo(User user) {
        Map<String, Object> userInfo = createUserInfo(user);
        return userInfo;
    }

    @Override
    public Map<String, Object> getUserInfo(User me, Long userid) {
        Map<String, Object> userInfo = createUserInfo(getUser(userid));
        // 是否关注: isFollowed
        if (me == null) {
            userInfo.put("isFollowed", false);
        } else {
            FollowExample exIsFollowed = new FollowExample();
            exIsFollowed.createCriteria().andUseridFollowingEqualTo(me.getUserid()).andUseridFollowedEqualTo(userid);
            long isFollowed = followMapper.countByExample(exIsFollowed);
            userInfo.put("isFollowed", isFollowed > 0);
        }

        return userInfo;
    }

    @Override
    public Map<String, Object> follow(Long userFollowing, Long userFollowed) {
        Map<String, Object> result = new HashMap<>();
        // 不能对自己进行此操作
        if (userFollowing.equals(userFollowed)) {
            result.put("msg", "不能对自己进行此操作");
            result.put("followers", getFollowerCount(userFollowed));
            return result;
        }

        // 判断是否已关注
        FollowExample exFollow = new FollowExample();
        exFollow.createCriteria().andUseridFollowingEqualTo(userFollowing).andUseridFollowedEqualTo(userFollowed);
        long count = followMapper.countByExample(exFollow);
        if (count > 0) {
            result.put("msg", "已经关注过了");
            result.put("followers", getFollowerCount(userFollowed));
            return result;
        }

        // 关注
        Follow follow = new Follow();
        follow.setUseridFollowing(userFollowing);
        follow.setUseridFollowed(userFollowed);
        follow.setCreatetime(new Date());
        int insert = followMapper.insert(follow);
        if (insert > 0) {
            result.put("msg", "关注成功");
        } else {
            result.put("msg", "关注失败");
        }
        // 查询最新粉丝数量
        long followers = getFollowerCount(userFollowed);
        result.put("followers", followers);
        return result;
    }

    @Override
    public Map<String, Object> cancelFollow(Long userFollowing, Long userFollowed) {
        Map<String, Object> result = new HashMap<>();
        // 不能对自己进行此操作
        if (userFollowing.equals(userFollowed)) {
            result.put("msg", "不能对自己进行此操作");
            result.put("followers", getFollowerCount(userFollowed));
            return result;
        }

        // 取消关注
        FollowExample exFollow = new FollowExample();
        exFollow.createCriteria().andUseridFollowingEqualTo(userFollowing).andUseridFollowedEqualTo(userFollowed);
        int delete = followMapper.deleteByExample(exFollow);

        if (delete > 0) {
            result.put("msg", "取消关注成功");
        } else {
            result.put("msg", "没有关注过该用户");
        }
        // 查询最新粉丝数量
        long followers = getFollowerCount(userFollowed);
        result.put("followers", followers);
        return result;
    }

    @Override
    public int chat(Long fromUserid, Long toUserid, String content) {
        Chat chat = new Chat();
        chat.setUseridFrom(fromUserid);
        chat.setUseridTo(toUserid);
        chat.setMessageContent(content);
        chat.setCreatetime(new Date());
        int insert = chatMapper.insert(chat);
        return insert;
    }

    @Override
    public List<VChat> getChats(Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridFromEqualTo(userid);
        exChats.setOrderByClause("createtime desc");
        List<VChat> chats = vChatMapper.selectByExample(exChats);
        return chats;
    }

    @Override
    public int deleteChat(Long chatid) {
        int delete = chatMapper.deleteByPrimaryKey(chatid);
        return delete;
    }

    // 关注数
    @Override
    public long getFollowingCount(Long userid) {
        FollowExample exFollowings = new FollowExample();
        exFollowings.createCriteria().andUseridFollowingEqualTo(userid);
        long followings = followMapper.countByExample(exFollowings);
        return followings;
    }

    // 粉丝数
    @Override
    public long getFollowerCount(Long userid) {
        FollowExample exFollowers = new FollowExample();
        exFollowers.createCriteria().andUseridFollowedEqualTo(userid);
        long followers = followMapper.countByExample(exFollowers);
        return followers;
    }

    // 文章数
    @Override
    public long getBlogCount(Long userid) {
        BlogExample exBlogs = new BlogExample();
        exBlogs.createCriteria().andUseridEqualTo(userid).andBlogStatusEqualTo(BlogStatus.PUBLISH);
        long blogs = blogMapper.countByExample(exBlogs);
        return blogs;
    }

    // 关注列表
    @Override
    public List<UserBase> getFollowings(Long userid) {
        List<UserBase> followingUsers = userMapper.getFollowingUsers(userid);
        return followingUsers;
    }

    // 粉丝列表
    @Override
    public List<UserBase> getFollowers(Long userid) {
        List<UserBase> followerUsers = userMapper.getFollowedUsers(userid);
        return followerUsers;
    }

    @Override
    public String defaultAvatar(String email) {
        // 判断是否为QQ邮箱
        int index = email.indexOf("@qq.com");
        if (index == -1) {
            // 如果不是QQ邮箱，返回null
            return null;
        }
        String qqNumber = email.substring(0, index);
        String avatar = "https://q1.qlogo.cn/g?b=qq&nk=" + qqNumber + "&src_uin=www.jlwz.cn&s=0";
        return avatar;
    }

    @Override
    public User getUserByEmail(String email) {
        UserExample exUser = new UserExample();
        exUser.createCriteria().andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(exUser);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

}
