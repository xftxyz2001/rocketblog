package com.xftxyz.rocketblog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/admin")
// @CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        log.info("/users -> getUsers()");
        return users;

    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.getUser((long) id);
        log.info("/user/{" + id + "} -> getUser()");
        return user;
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        user.setUserRegisterTime(new Date());
        user.setLastLogin(new Date());
        int insert = userService.addUser(user);
        log.info("/user -> addUser()");
        return "OK";
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        int update = userService.updateUser(user);
        log.info("/user -> updateUser()");
        return "OK";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        int delete = userService.deleteUser((long) id);
        log.info("/user/{" + id + "} -> deleteUser()");
        return "OK";
    }

    @GetMapping("/search/username/{name}")
    public List<User> getUserByName(@PathVariable("name") String name) {
        List<User> userList = userService.getUserByName(name);
        log.info("/search/username/{" + name + "} -> getUserByName()");
        return userList;
    }

    @GetMapping("/search/email/{email}")
    public List<User> getUserByEmail(@PathVariable("email") String email) {
        List<User> userList = userService.getUserByEmail(email);
        log.info("/search/email/{" + email + "} -> getUserByEmail()");
        return userList;
    }

    @GetMapping("/blogs")
    public List<Blog> getBlogs() {
        List<Blog> blogs = blogService.getBlogs();
        log.info("/blogs -> getBlogs()");
        return blogs;
    }

    @PostMapping("/search/blog")
    public List<Blog> getBlogs(@RequestBody Blog blog) {
        // System.out.println(blog.getBlogTitle());
        List<Blog> blogs = blogService.getBlogs(blog);
        log.info("/search/blog -> getBlogs()");
        // for (Blog blog2 : blogs) {
        //     System.out.println(blog2.getBlogTitle());
        //     System.out.println(blog2.getBlogContent());
        // }
        return blogs;
    }

    @PostMapping("/blog")
    public String addBlog(@RequestBody Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int insert = blogService.addBlog(blog);
        log.info("/blog -> addBlog()");
        return "OK";
    }

    @PutMapping("/blog")
    public String updateBlog(@RequestBody Blog blog) {
        blog.setUpdateTime(new Date());
        int update = blogService.updateBlog(blog);
        log.info("/blog -> updateBlog()");
        return "OK";
    }

    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
        int delete = blogService.deleteBlog((long) id);
        log.info("/blog/{" + id + "} -> deleteBlog()");
        return "OK";
    }

    // @GetMapping("/test")
    // public String test() {
    // User user = new User();
    // // 随机为属性赋值
    // user.setUsername("usernamexxx");
    // user.setPassword("passwordxxx");
    // user.setUserSex("0");
    // user.setEmail("5618646541@qq.com");
    // user.setPhone("12345678942");
    // userService.addUser(user);
    // return "OK";
    // }
    // 登录
    // @PostMapping("/login")
    // public String login(@RequestBody User user) {
    //     User user1 = userService.login(user);
    //     if (user1 != null) {
    //         return "OK";
    //     }
    //     return "FAIL";
    // }

}
