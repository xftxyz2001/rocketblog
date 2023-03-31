package com.xftxyz.rocketblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;
import com.xftxyz.rocketblog.service.BlogService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/publish")
    public Result<Object> publish(@RequestBody Blog blog, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.fail(ResultCode.USER_NOT_LOGIN);
        }
        blog.setUserid(user.getUserid());
        blog.setBlogStatus(0);
        if (blog.getBlogTitle() == null || blog.getBlogTitle().equals("")) {
            blog.setBlogTitle("无标题");
        }
        // 日志：用户{}发布了博客{}
        log.info("用户{}发布了博客{}", user.getUsername(), blog.getBlogTitle());
        int insert = blogService.addBlog(blog);
        return Result.success(insert);
    }
}
