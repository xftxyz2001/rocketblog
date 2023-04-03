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
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @GetMapping("/users")
    public PageInfo<User> getUsers(@RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.getUsers();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.getUser((long) id);
        return user;
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        user.setUserRegisterTime(new Date());
        user.setLastLogin(new Date());
        int insert = userService.addUser(user);
        return String.valueOf(insert);
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        int update = userService.updateUser(user);
        return String.valueOf(update);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        int delete = userService.deleteUser((long) id);
        return String.valueOf(delete);
    }

    @GetMapping("/search/username/{name}")
    public PageInfo<User> getUserByName(@PathVariable("name") String name,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.getUsersLikeName(name);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @GetMapping("/search/email/{email}")
    public PageInfo<User> getUserByEmail(@PathVariable("email") String email,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.getUsersLikeEmail(email);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @GetMapping("/blogs")
    public PageInfo<Blog> getBlogs(@RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogService.getBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @PostMapping("/search/blog")
    public PageInfo<Blog> getBlogs(@RequestBody Blog blog, @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogService.getBlogs(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @PostMapping("/blog")
    public String addBlog(@RequestBody Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int insert = blogService.addBlog(blog);
        return String.valueOf(insert);
    }

    @PutMapping("/blog")
    public String updateBlog(@RequestBody Blog blog) {
        blog.setUpdateTime(new Date());
        int update = blogService.updateBlog(blog);
        return String.valueOf(update);
    }

    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
        int delete = blogService.deleteBlog((long) id);
        return String.valueOf(delete);
    }

}
