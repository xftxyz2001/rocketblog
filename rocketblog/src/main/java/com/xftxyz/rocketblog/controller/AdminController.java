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
import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.FileInfo;
import com.xftxyz.rocketblog.pojo.SysOption;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.UserInfo;
import com.xftxyz.rocketblog.service.AdminService;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.service.CommentService;
import com.xftxyz.rocketblog.service.ImageService;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.validation.ValidInfo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

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

    @Autowired
    CommentService commentService;

    @Autowired
    ImageService imageService;

    @Autowired
    AdminService adminService;

    /**
     * 获取所有用户信息
     *
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含获取到的用户信息列表 {@link UserInfo}
     */
    @GetMapping("/users")
    public PageInfo<User> getUsers(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.getUsers();
        return new PageInfo<>(users);
    }

    /**
     * 获取指定用户信息
     *
     * @param id 指定用户的ID
     * @return 返回一个 {@link UserInfo} 对象，包含指定用户的信息
     */
    @GetMapping("/user/{id}")
    public UserInfo getUserInfo(
            @PathVariable("id") @Min(value = 1, message = ValidInfo.USER_ID_LESS_THAN_ONE) Long id) {
        return userService.getUserInfoById(id);
    }

    /**
     * 获取指定用户详细信息
     *
     * @param id 指定用户的ID
     * @return 返回一个 {@link User} 对象，包含指定用户的详细信息
     */
    @GetMapping("/user/detail/{id}")
    public User getUser(@PathVariable("id") @Min(value = 1, message = ValidInfo.USER_ID_LESS_THAN_ONE) Long id) {
        return userService.getUser(id);
    }

    /**
     * 添加用户
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
     * 修改用户信息
     *
     * @param user 要修改的用户对象
     * @return 返回一个整数值，表示修改用户信息的行数
     */
    @PutMapping("/user")
    public Integer updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除指定用户
     *
     * @param id 指定用户的ID
     * @return 返回一个整数值，表示删除用户的行数
     */
    @DeleteMapping("/user/{id}")
    public Integer deleteUser(@PathVariable("id") @Min(value = 1, message = ValidInfo.USER_ID_LESS_THAN_ONE) Long id) {
        return userService.deleteUser(id);
    }

    /**
     * 指定用户为管理员
     * 
     * @param userid 指定用户的ID
     * @return 返回一个整数值，表示修改用户信息的行数
     */
    @PutMapping("/addadmin/{userid}")
    public Integer addAdmin(
            @PathVariable("userid") @Min(value = 1, message = ValidInfo.USER_ID_LESS_THAN_ONE) Long userid) {
        return userService.addAdmin(userid);
    }

    /**
     * 根据用户对象的属性查找用户信息
     *
     * @param user     用户对象，根据该对象的属性查找用户信息
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含查找到的用户信息列表 {@link UserInfo}
     */
    @PostMapping("/user/s")
    public PageInfo<User> findUsersByExample(@RequestBody User user,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.findUsersByExample(user);
        return new PageInfo<>(userList);
    }

    /**
     * 
     * 获取所有博客
     * 
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含获取到的博客列表 {@link BlogInfo}
     */
    @GetMapping("/blogs")
    public PageInfo<BlogInfo> getBlogs(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.getAllBlogs();
        return new PageInfo<>(blogs);
    }

    /**
     * 
     * 获取指定博客
     * 
     * @param id 指定博客的ID
     * @return 返回一个 {@link BlogDetail} 对象，包含指定博客的详细信息
     */
    @GetMapping("/blog/{id}")
    public BlogDetail getBlog(@PathVariable("id") @Min(value = 1, message = ValidInfo.BLOG_ID_LESS_THAN_ONE) Long id) {
        return blogService.getBlogDetail(id, null);
    }

    /**
     * 
     * 根据博客对象的属性查找博客
     * 
     * @param blog     博客对象，根据该对象的属性查找博客
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含查找到的博客列表 {@link BlogInfo}
     */
    @PostMapping("/blog/s")
    public PageInfo<BlogInfo> getBlogs(@RequestBody BlogInfo blog,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.findByExample(blog);
        return new PageInfo<>(blogs);
    }

    /**
     * 
     * 添加博客
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
     * 修改博客
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
     * 删除指定博客
     * 
     * @param id 指定博客的ID
     * @return 返回一个整数值，表示删除博客的行数
     */
    @DeleteMapping("/blog/{id}")
    public Integer deleteBlog(@PathVariable("id") @Min(value = 1, message = ValidInfo.BLOG_ID_LESS_THAN_ONE) Long id) {
        return blogService.removeRF(id);
    }

    /**
     * 获取所有评论
     * 
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含获取到的评论列表 {@link Comment}
     */
    @GetMapping("/comments")
    public PageInfo<Comment> getComments(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentService.getAllComments();
        return new PageInfo<>(comments);
    }

    /**
     * 查找指定博客或用户的评论
     * 
     * @param comment  评论对象，根据该对象的属性查找评论
     * @param pageNum  分页页码，默认值为1
     * @param pageSize 分页大小，默认值为{@link EnvironmentVariables#DEFAULT_PAGE_SIZE}
     * @return 返回一个 {@link PageInfo} 对象，包含查找到的评论列表 {@link Comment}
     */
    @PostMapping("/search/comment")
    public PageInfo<Comment> getComments(@RequestBody Comment comment,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentService.findCommentsByExample(comment);
        return new PageInfo<>(comments);
    }

    /**
     * 删除指定评论
     * 
     * @param commentId 评论ID
     * @return 返回一个整数值，表示删除评论的行数
     */
    @DeleteMapping("/comment/{commentId}")
    public Integer deleteComment(
            @PathVariable("commentId") @Min(value = 1, message = ValidInfo.COMMENT_ID_LESS_THAN_ONE) Long commentId) {
        return commentService.removeComment(commentId);
    }

    /**
     * 获取所有图片文件名
     * 
     * @return 返回一个 {@link List} 对象，包含所有图片文件名
     */
    @GetMapping("/images")
    public List<FileInfo> getAllImageIds(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = ValidInfo.PAGE_LESS_THAN_ONE) Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return imageService.getAllImageFileInfo(pageNum, pageSize);
    }

    /**
     * 删除指定图片
     * 
     * @param filename 指定图片的文件名
     */
    @DeleteMapping("/image/{filename}")
    public void deleteImage(@PathVariable("filename") @NotBlank(message = "文件名不能为空") String filename) {
        imageService.deleteImageByName(filename);
    }

    /**
     * 系统操作
     * 
     * @return 返回一个 {@link List} 对象，包含系统操作列表 {@link SysOption}
     */
    @GetMapping("/system")
    public List<SysOption> getSystemOption() {
        return EnvironmentVariables.SYS_OPTIONS;
    }

    /**
     * 重置数据库，仅限管理员使用
     * 
     * @param session 会话
     * @return 返回一个字符串，表示重置数据库的结果
     */
    @GetMapping("/reset")
    public String reset(HttpSession session) {
        // 重置数据库
        adminService.resetDatabase();
        // 清空图片
        adminService.deleteImageFiles();
        // 删除日志文件
        adminService.deleteLogFiles();
        return "重置成功";
    }

}
