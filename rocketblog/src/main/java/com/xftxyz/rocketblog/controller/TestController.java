package com.xftxyz.rocketblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.service.UserService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

    // 测试除0
    @GetMapping("/by0")
    public String by0(@RequestParam("num") Float num) {
        return String.valueOf(num / 0);
    }
}
