package com.xftxyz.rocketblog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.service.UserService;

/**
 * 后台管理相关
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @GetMapping("/users")
    public PageInfo<User> getUsers(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.getUsers();
        return new PageInfo<>(users);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping("/user")
    public Integer addUser(@RequestBody User user) {
        user.setUserRegisterTime(new Date());
        user.setLastLogin(new Date());
        return userService.addUser(user);
    }

    @PutMapping("/user")
    public Integer updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public Integer deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/search/username/{name}")
    public PageInfo<User> getUserByName(@PathVariable("name") String name,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.getUsersLikeName(name);
        return new PageInfo<>(userList);
    }

    @GetMapping("/search/email/{email}")
    public PageInfo<User> getUserByEmail(@PathVariable("email") String email,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.getUsersLikeEmail(email);
        return new PageInfo<>(userList);
    }

    @GetMapping("/blogs")
    public PageInfo<BlogInfo> getBlogs(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.getAllBlogs();
        return new PageInfo<>(blogs);
    }

    @PostMapping("/search/blog")
    public PageInfo<BlogInfo> getBlogs(@RequestBody BlogInfo blog, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.findByExample(blog);
        return new PageInfo<>(blogs);
    }

    @PostMapping("/blog")
    public Integer addBlog(@RequestBody Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        return blogService.add(blog);
    }

    @PutMapping("/blog")
    public Integer updateBlog(@RequestBody Blog blog) {
        blog.setUpdateTime(new Date());
        return blogService.updateBlog(blog);
    }

    @DeleteMapping("/blog/{id}")
    public Integer deleteBlog(@PathVariable("id") Long id) {
        return blogService.removeRF(id);
    }

}
