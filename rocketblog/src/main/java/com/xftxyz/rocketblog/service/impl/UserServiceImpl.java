package com.xftxyz.rocketblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.user.AlreadyDoneException;
import com.xftxyz.rocketblog.exception.user.CaptchaErrorException;
import com.xftxyz.rocketblog.exception.user.EmailExistException;
import com.xftxyz.rocketblog.exception.user.EmailOrPasswordErrorException;
import com.xftxyz.rocketblog.exception.user.PasswordErrorException;
import com.xftxyz.rocketblog.exception.user.SelfOperationException;
import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.ChatMapper;
import com.xftxyz.rocketblog.mapper.FollowMapper;
import com.xftxyz.rocketblog.mapper.UserMapper;
import com.xftxyz.rocketblog.mapper.VChatMapper;
import com.xftxyz.rocketblog.pojo.BlogExample;
import com.xftxyz.rocketblog.pojo.Follow;
import com.xftxyz.rocketblog.pojo.FollowExample;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserExample;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.status.BlogStatus;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    StringRedisTemplate redisTemplate;

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
    public List<UserInfo> getUserInfos() {
        List<User> users = userMapper.selectByExample(null);
        List<UserInfo> userInfos = new ArrayList<>();
        for (User user : users) {
            userInfos.add(getUserInfo(user));
        }
        return userInfos;
    }

    @Override
    public User getUser(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public Integer addUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public Integer deleteUser(Long id) {
        int delete = userMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public Integer updateUser(User user) {
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
    public void register(String name, String password, String email) {

        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailEqualTo(email);
        long countEmail = userMapper.countByExample(exEmail);
        if (countEmail > 0) {
            throw new EmailExistException();
        }
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);

        // 默认头像
        user.setAvatar(defaultAvatar(email));

        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
    }

    @Override
    public User login(String email, String password) {
        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailEqualTo(email).andPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(exEmail);
        if (userList.size() < 1) {
            throw new EmailOrPasswordErrorException();
        }
        User user = userList.get(0);
        // 更新最后登录时间
        user.setLastLogin(new Date());
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    public UserInfo getUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(user.getUserid());
        userInfo.setUsername(user.getUsername());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setFollowings(getFollowingCount(user.getUserid()));
        userInfo.setFollowers(getFollowerCount(user.getUserid()));
        userInfo.setBlogs(getBlogCount(user.getUserid()));
        return userInfo;
    }

    @Override
    public UserInfo getUserInfo(User me, Long userid) {
        UserInfo userInfo = getUserInfo(getUser(userid));
        // 是否关注: isFollowed
        if (me != null) {
            FollowExample exIsFollowed = new FollowExample();
            exIsFollowed.createCriteria().andUseridFollowingEqualTo(me.getUserid()).andUseridFollowedEqualTo(userid);
            long isFollowed = followMapper.countByExample(exIsFollowed);
            userInfo.setFollowed(isFollowed > 0);
        }
        return userInfo;
    }

    @Override
    public Long follow(Long userFollowing, Long userFollowed) {
        // 不能对自己进行此操作
        if (userFollowing.equals(userFollowed)) {
            throw new SelfOperationException();
        }

        // 判断是否已关注
        FollowExample exFollow = new FollowExample();
        exFollow.createCriteria().andUseridFollowingEqualTo(userFollowing).andUseridFollowedEqualTo(userFollowed);
        long count = followMapper.countByExample(exFollow);
        if (count > 0) {
            throw new AlreadyDoneException("已关注过该用户");
        }

        // 关注
        Follow follow = new Follow();
        follow.setUseridFollowing(userFollowing);
        follow.setUseridFollowed(userFollowed);
        follow.setCreatetime(new Date());
        followMapper.insert(follow);
        // 查询最新粉丝数量
        long followers = getFollowerCount(userFollowed);
        return followers;
    }

    @Override
    public Long cancelFollow(Long userFollowing, Long userFollowed) {
        // 不能对自己进行此操作
        if (userFollowing.equals(userFollowed)) {
            throw new SelfOperationException();
        }

        // 取消关注
        FollowExample exFollow = new FollowExample();
        exFollow.createCriteria().andUseridFollowingEqualTo(userFollowing).andUseridFollowedEqualTo(userFollowed);
        followMapper.deleteByExample(exFollow);
        // 查询最新粉丝数量
        long followers = getFollowerCount(userFollowed);
        return followers;
    }

    // 关注数
    @Override
    public Long getFollowingCount(Long userid) {
        FollowExample exFollowings = new FollowExample();
        exFollowings.createCriteria().andUseridFollowingEqualTo(userid);
        long followings = followMapper.countByExample(exFollowings);
        return followings;
    }

    // 粉丝数
    @Override
    public Long getFollowerCount(Long userid) {
        FollowExample exFollowers = new FollowExample();
        exFollowers.createCriteria().andUseridFollowedEqualTo(userid);
        long followers = followMapper.countByExample(exFollowers);
        return followers;
    }

    // 文章数
    @Override
    public Long getBlogCount(Long userid) {
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

    @Override
    public User fromToken(String token) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        String userid = redisTemplate.boundValueOps(token).get();
        if (userid == null) {
            return null;
        }
        return getUser(Long.valueOf(userid));
    }

    @Override
    public String toToken(String sessionId, User user) {
        redisTemplate.boundValueOps(sessionId).set(String.valueOf(user.getUserid()),
                EnvironmentVariables.COOKIE_TOKEN_EXPIRE, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        User user = getUser(id);
        return getUserInfo(user);
    }

    @Override
    public List<UserInfo> findUserInfosByExample(User user) {
        UserExample exUser = new UserExample();
        UserExample.Criteria criteria = exUser.createCriteria();
        if (user != null) {
            if (StringUtils.hasLength(user.getUsername())) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (StringUtils.hasLength(user.getEmail())) {
                criteria.andEmailLike("%" + user.getEmail() + "%");
            }
        }
        List<User> users = userMapper.selectByExample(exUser);
        List<UserInfo> userInfos = new ArrayList<>();
        for (User u : users) {
            UserInfo userInfo = getUserInfo(u);
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @Override
    public void checkCaptcha(String email, String vertify) {
        String captcha = redisTemplate.boundValueOps(email).get();
        if (captcha == null) {
            throw new CaptchaErrorException("验证码已过期");
        }
        if (!captcha.equals(vertify)) {
            throw new CaptchaErrorException("验证码错误");
        }
    }

    @Override
    public void changePassword(User user, String password, String newPassword) {

        // 如果原密码不正确，则抛出 PasswordErrorException 异常
        if (!user.getPassword().equals(password)) {
            throw new PasswordErrorException();
        }

        // 更新用户密码并保存到数据库中
        user.setPassword(newPassword);
        updateUser(user);

        deleteUserTokens(user.getUserid());

    }

    @Override
    public boolean checkPassword(User user) {
        // 根据id查询用户
        User dbUser = getUser(user.getUserid());
        // 用户存在且密码正确
        return dbUser != null && dbUser.getPassword().equals(user.getPassword());
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public void deleteUserTokens(Long userid) {
        // ！！！非常不好的做法！！！
        // 清除用户登录状态，遍历redis中的所有key，如果value为用户id，则删除
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            String value = redisTemplate.boundValueOps(key).get();
            if (value != null && value.equals(String.valueOf(userid))) {
                redisTemplate.delete(key);
            }
        }
    }
}
