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

    // 除零
    @GetMapping("/by0")
    public Double by0(@RequestParam("num") Double num) {
        return num / 0;
    }

    // 空返回
    @GetMapping("/null")
    public void nullReturn() {
    }
}
