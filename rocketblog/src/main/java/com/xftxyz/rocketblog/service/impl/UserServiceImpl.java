package com.xftxyz.rocketblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.user.AlreadyDoneException;
import com.xftxyz.rocketblog.exception.user.EmailExistException;
import com.xftxyz.rocketblog.exception.user.SelfOperationException;
import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.ChatMapper;
import com.xftxyz.rocketblog.mapper.FollowMapper;
import com.xftxyz.rocketblog.mapper.UserMapper;
import com.xftxyz.rocketblog.mapper.VChatMapper;
import com.xftxyz.rocketblog.pojo.BlogExample;
import com.xftxyz.rocketblog.pojo.Chat;
import com.xftxyz.rocketblog.pojo.ChatInfo;
import com.xftxyz.rocketblog.pojo.Follow;
import com.xftxyz.rocketblog.pojo.FollowExample;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserBase;
import com.xftxyz.rocketblog.pojo.UserExample;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.pojo.VChat;
import com.xftxyz.rocketblog.pojo.VChatExample;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.status.BlogStatus;
import com.xftxyz.rocketblog.status.ReadStatus;

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
            return null;
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
        if (me == null) {
            userInfo.setFollowed(false);
        } else {
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

    @Override
    public int chat(Long fromUserid, Long toUserid, String content) {
        Chat chat = new Chat();
        chat.setUseridFrom(fromUserid);
        chat.setUseridTo(toUserid);
        chat.setMessageContent(content);
        chat.setCreatetime(new Date());
        chat.setRead(ReadStatus.UNREAD);
        int insert = chatMapper.insert(chat);
        return insert;
    }

    @Override
    public List<VChat> getChats(Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridToEqualTo(userid); // 发给我的
        exChats.or().andUseridFromEqualTo(userid); // 我发出的
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

    @Override
    public List<ChatInfo> getSessionList(User user) {
        List<VChat> chats = getChats(user.getUserid());
        Map<Long, List<VChat>> map = new HashMap<>();
        for (VChat chat : chats) {
            // 获取对方的userid
            Long userid = chat.getUseridFrom().equals(user.getUserid()) ? chat.getUseridTo() : chat.getUseridFrom();
            if (map.containsKey(userid)) {
                map.get(userid).add(chat);
            } else {
                List<VChat> list = new ArrayList<>();
                list.add(chat);
                map.put(userid, list);
            }
        }
        List<ChatInfo> chatInfos = new ArrayList<>();
        for (Long userid : map.keySet()) {
            chatInfos.add(createChatInfo(userid, map.get(userid)));
        }
        return chatInfos;
    }

    @Override
    public ChatInfo getSession(User user, Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridFromEqualTo(user.getUserid()).andUseridToEqualTo(userid); // 我发给对方的
        exChats.or().andUseridFromEqualTo(userid).andUseridToEqualTo(user.getUserid()); // 对方发给我的
        exChats.setOrderByClause("createtime desc");
        List<VChat> chats = vChatMapper.selectByExample(exChats);
        return createChatInfo(userid, chats);
    }

    // userid：别人
    private ChatInfo createChatInfo(Long userid, List<VChat> chats) {
        if (chats.size() < 0) {
            return null;
        }
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setUserid(userid); // 对方的userid
        VChat lastChat = chats.get(0);
        Long useridFrom = lastChat.getUseridFrom();
        chatInfo.setUsername(useridFrom == userid ? lastChat.getFromUsername() : lastChat.getToUsername());
        chatInfo.setAvatar(useridFrom == userid ? lastChat.getFromAvatar() : lastChat.getToAvatar());
        chatInfo.setLastMsg(lastChat.getMessageContent());
        chatInfo.setLastTime(lastChat.getCreatetime());
        int count = 0;
        for (VChat chat : chats) {
            // from是别人，且未读
            if (chat.getUseridFrom() == userid && chat.getRead() == ReadStatus.UNREAD) {
                count++;
            }
        }
        chatInfo.setMsgNum(count);
        return chatInfo;
    }

    @Override
    public List<VChat> getChatDetail(User user, Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridFromEqualTo(user.getUserid()).andUseridToEqualTo(userid); // 我发给对方的
        exChats.or().andUseridFromEqualTo(userid).andUseridToEqualTo(user.getUserid()); // 对方发给我的
        exChats.setOrderByClause("createtime desc");
        List<VChat> chats = vChatMapper.selectByExample(exChats);
        // 更新为已读
        updateRead(chats, userid);
        return chats;
    }

    private void updateRead(List<VChat> chats, Long userid) {
        for (VChat vchat : chats) {
            if (vchat.getUseridFrom() == userid && vchat.getRead() == ReadStatus.UNREAD) {
                Chat chat = new Chat();
                chat.setChatId(vchat.getChatId());
                chat.setRead(ReadStatus.READ);
                chatMapper.updateByPrimaryKeySelective(chat);
            }
        }
    }

    @Override
    public int deleteSession(User user, Long userid) {
        VChatExample exChats = new VChatExample();
        exChats.createCriteria().andUseridFromEqualTo(user.getUserid()).andUseridToEqualTo(userid); // 我发给对方的
        exChats.or().andUseridFromEqualTo(userid).andUseridToEqualTo(user.getUserid()); // 对方发给我的
        int delete = vChatMapper.deleteByExample(exChats);
        return delete;
    }

    @Override
    public User fromToken(String token) {
        // 从token中提取userid和password，尝试登录
        int index = token.indexOf("#");
        String userid = token.substring(0, index);
        String password = token.substring(index + 1);
        User user = null;
        if (StringUtils.hasLength(userid) && StringUtils.hasLength(password)) {
            user = getUser(Long.parseLong(userid));
        }
        return user;
    }

    @Override
    public String toToken(User user) {
        return user.getUserid() + "#" + user.getPassword();
    }

}
