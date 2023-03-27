package com.xftxyz.rocketblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.mapper.UserMapper;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserExample;
import com.xftxyz.rocketblog.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

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
    public List<User> getUserByName(String name) {
        UserExample exName = new UserExample();
        exName.createCriteria().andUsernameLike("%"+name+"%");
        List<User> userList = userMapper.selectByExample(exName);
        return userList;
    }

    @Override
    public List<User> getUserByEmail(String email) {
        UserExample exEmail = new UserExample();
        exEmail.createCriteria().andEmailLike("%"+email+"%");
        List<User> userList = userMapper.selectByExample(exEmail);
        return userList;
    }
   
}
