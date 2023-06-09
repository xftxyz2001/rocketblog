package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.captcha.CaptchaErrorException;
import com.xftxyz.rocketblog.exception.user.AlreadyDoneException;
import com.xftxyz.rocketblog.exception.user.EmailExistException;
import com.xftxyz.rocketblog.exception.user.EmailOrPasswordErrorException;
import com.xftxyz.rocketblog.exception.user.IllegalOperationException;
import com.xftxyz.rocketblog.exception.user.PasswordErrorException;
import com.xftxyz.rocketblog.exception.user.SelfOperationException;
import com.xftxyz.rocketblog.exception.user.UserNotExistException;
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
import com.xftxyz.rocketblog.status.RoleStatus;

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
    public List<User> getUsers() {
        return userMapper.selectByExample(null);
    }

    @Override
    public User getUser(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer deleteUser(Long id) {
        // 检测被删除的用户是不是管理员
        User user = userMapper.selectByPrimaryKey(id);
        if (user.getIsSuperuser() == RoleStatus.ADMIN) {
            throw new IllegalOperationException("不能删除管理员");
        }
        return cancellation(id);
    }

    @Override
    public Integer cancellation(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
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
        // 检查邮箱是否已注册
        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailEqualTo(email);
        long countEmail = userMapper.countByExample(exEmail);
        if (countEmail > 0) {
            throw new EmailExistException();
        }
        User user = new User();
        // 默认用户名：邮箱前缀
        user.setUsername(StringUtils.hasLength(name) ? name : email.substring(0, email.indexOf("@")));
        user.setPassword(password);
        user.setEmail(email);

        // 默认头像：qq邮箱头像（其他暂未处理）
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
        int index = token.indexOf("_");
        if (index == -1) {
            return null;
        }
        String userid = token.substring(0, index);
        String tokenSessionId = token.substring(index + 1);
        String sSessionId = redisTemplate.boundValueOps(userid).get();
        if (tokenSessionId == null || !tokenSessionId.equals(sSessionId)) {
            return null;
        }
        return getUser(Long.valueOf(userid));
    }

    @Override
    public String toToken(String sessionId, User user) {
        Long userid = user.getUserid();
        String token = userid + "_" + sessionId;
        redisTemplate.boundValueOps(String.valueOf(userid)).set(sessionId,
                EnvironmentVariables.COOKIE_TOKEN_EXPIRE, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        User user = getUser(id);
        return getUserInfo(user);
    }

    @Override
    public List<User> findUsersByExample(User user) {
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
        return userMapper.selectByExample(exUser);
    }

    @Override
    public boolean checkCaptcha(String email, String vertify) {
        String captcha = redisTemplate.boundValueOps(email).get();
        if (captcha == null) {
            throw new CaptchaErrorException("验证码已过期");
        }
        if (!captcha.equals(vertify)) {
            throw new CaptchaErrorException("验证码错误");
        }
        // 验证码正确，删除验证码
        redisTemplate.delete(email);
        return true;
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

        deleteUserToken(user.getUserid());

    }

    @Override
    public boolean checkPassword(User user) {
        // 根据id查询用户
        User dbUser = getUser(user.getUserid());
        // 用户存在且密码正确
        return dbUser != null && dbUser.getPassword().equals(user.getPassword());
    }

    @Override
    public void deleteUserToken(Long userid) {
        redisTemplate.delete(userid.toString());
    }

    @Override
    public void updateUser(User user, String username, String userSex, String phone, String avatar) {

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
        updateUser(user);
    }

    @Override
    public Integer addAdmin(Long userid) {
        User user = getUser(userid);
        if (user == null) {
            throw new UserNotExistException("用户不存在");
        }
        if (user.getIsSuperuser() == RoleStatus.ADMIN) {
            throw new IllegalOperationException("该用户已经是管理员");
        }
        user.setIsSuperuser(RoleStatus.ADMIN);
        return updateUser(user);
    }

    @Override
    public Integer removeAdmin(Long userid) {
        User user = getUser(userid);
        if (user == null) {
            throw new UserNotExistException("用户不存在");
        }
        if (user.getIsSuperuser() != RoleStatus.ADMIN) {
            throw new IllegalOperationException("该用户不是管理员");
        }
        user.setIsSuperuser(RoleStatus.USER);
        return updateUser(user);
    }
}
