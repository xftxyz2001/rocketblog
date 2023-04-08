package com.xftxyz.rocketblog.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 返回除零错误。
     *
     * @param num 要除以零的数字
     * @return 除以零的结果
     */
    @GetMapping("/by0")
    public Double by0(@RequestParam("num") Double num) {
        return num / 0;
    }

    /**
     * 空返回测试方法。
     */
    @GetMapping("/null")
    public void nullReturn() {
    }

    /**
     * 获取当前时间。
     *
     * @return 返回一个 {@link User} 对象，包含当前时间信息
     */
    @GetMapping("/time")
    public User time() {
        User user = new User();
        user.setLastLogin(new Date());
        user.setUserRegisterTime(new Date());
        return user;
    }

}
