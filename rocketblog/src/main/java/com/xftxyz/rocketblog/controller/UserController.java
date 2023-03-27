package com.xftxyz.rocketblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.mapper.UserMapper;
import com.xftxyz.rocketblog.pojo.User;

@RestController
public class UserController {
    
    @Autowired
    UserMapper userMapper;
    
    // 获取用户列表
    // @GetMapping("/users")
    // @CrossOrigin(origins = "*")
    // public List<User> getUsers() {
    //     List<User> selectByExample = userMapper.selectByExample(null);
    //     return selectByExample;
        
    // }

}
