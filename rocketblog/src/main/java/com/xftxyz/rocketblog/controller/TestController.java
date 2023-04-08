package com.xftxyz.rocketblog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.UserService;

/**
 * 测试相关
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

    // 除零
    @GetMapping("/by0")
    public Double by0(@RequestParam("num") Double num) {
        return num / 0;
    }

    // 空返回
    @GetMapping("/null")
    public void nullReturn() {
    }

    // 时间
    @GetMapping("/time")
    public Map<String, Object> time() {
        User user  = new User();
        user.setLastLogin(new Date());
        user.setUserRegisterTime(new Date());
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setCreateTime(new Date());
        blogInfo.setUpdateTime(new Date());

        return new HashMap<String, Object>() {{
            put("user", user);
            put("blogInfo", blogInfo);
        }};
    }

}
