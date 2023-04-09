package com.xftxyz.rocketblog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.BlogDetail;
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.service.UserService;

import jakarta.validation.constraints.Min;

/**
 * 后台管理相关
 */
@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    /**
     * 获取所有用户信息。
     *
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含获取到的用户信息列表 {@link UserInfo}
     */
    @GetMapping("/users")
    public PageInfo<UserInfo> getUserInfos(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> users = userService.getUserInfos();
        return new PageInfo<>(users);
    }

    /**
     * 获取指定用户信息。
     *
     * @param id 指定用户的ID
     * @return 返回一个 {@link UserInfo} 对象，包含指定用户的信息
     */
    @GetMapping("/user/{id}")
    public UserInfo getUserInfo(@PathVariable("id") @Min(value = 1, message = "目标用户ID不合法") Long id) {
        return userService.getUserInfoById(id);
    }

    /**
     * 获取指定用户详细信息。
     *
     * @param id 指定用户的ID
     * @return 返回一个 {@link User} 对象，包含指定用户的详细信息
     */
    @GetMapping("/user/detail/{id}")
    public User getUser(@PathVariable("id") @Min(value = 1, message = "目标用户ID不合法") Long id) {
        return userService.getUser(id);
    }

    /**
     * 添加用户。
     *
     * @param user 要添加的用户对象
     * @return 返回一个整数值，表示添加用户的行数
     */
    @PostMapping("/user")
    public Integer addUser(@RequestBody User user) {
        user.setUserRegisterTime(new Date());
        user.setLastLogin(new Date());
        return userService.addUser(user);
    }

    /**
     * 修改用户信息。
     *
     * @param user 要修改的用户对象
     * @return 返回一个整数值，表示修改用户信息的行数
     */
    @PutMapping("/user")
    public Integer updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除指定用户。
     *
     * @param id 指定用户的ID
     * @return 返回一个整数值，表示删除用户的行数
     */
    @DeleteMapping("/user/{id}")
    public Integer deleteUser(@PathVariable("id") @Min(value = 1, message = "目标用户ID不合法") Long id) {
        return userService.deleteUser(id);
    }

    /**
     * 根据用户对象的属性查找用户信息。
     *
     * @param user     用户对象，根据该对象的属性查找用户信息
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含查找到的用户信息列表 {@link UserInfo}
     */
    @PostMapping("/user/s")
    public PageInfo<UserInfo> findUserInfosByExample(@RequestBody User user,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userList = userService.findUserInfosByExample(user);
        return new PageInfo<>(userList);
    }

    /**
     * 
     * 获取所有博客。
     * 
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含获取到的博客列表 {@link BlogInfo}
     */
    @GetMapping("/blogs")
    public PageInfo<BlogInfo> getBlogs(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.getAllBlogs();
        return new PageInfo<>(blogs);
    }

    /**
     * 
     * 获取指定博客。
     * 
     * @param id 指定博客的ID
     * @return 返回一个 {@link BlogDetail} 对象，包含指定博客的详细信息
     */
    @GetMapping("/blog/{id}")
    public BlogDetail getBlog(@PathVariable("id") @Min(value = 1, message = "目标博客ID不合法") Long id) {
        return blogService.getBlogDetail(id, null);
    }

    /**
     * 
     * 根据博客对象的属性查找博客。
     * 
     * @param blog     博客对象，根据该对象的属性查找博客
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含查找到的博客列表 {@link BlogInfo}
     */
    @PostMapping("/blog/s")
    public PageInfo<BlogInfo> getBlogs(@RequestBody BlogInfo blog,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.findByExample(blog);
        return new PageInfo<>(blogs);
    }

    /**
     * 
     * 添加博客。
     * 
     * @param blog 要添加的博客对象
     * @return 返回一个整数值，表示添加博客的行数
     */
    @PostMapping("/blog")
    public Integer addBlog(@RequestBody Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        return blogService.add(blog);
    }

    /**
     * 
     * 修改博客。
     * 
     * @param blog 要修改的博客对象
     * @return 返回一个整数值，表示修改博客的行数
     */
    @PutMapping("/blog")
    public Integer updateBlog(@RequestBody Blog blog) {
        blog.setUpdateTime(new Date());
        return blogService.updateBlog(blog);
    }

    /**
     * 
     * 删除指定博客。
     * 
     * @param id 指定博客的ID
     * @return 返回一个整数值，表示删除博客的行数
     */
    @DeleteMapping("/blog/{id}")
    public Integer deleteBlog(@PathVariable("id") @Min(value = 1, message = "目标博客ID不合法") Long id) {
        return blogService.removeRF(id);
    }

}
