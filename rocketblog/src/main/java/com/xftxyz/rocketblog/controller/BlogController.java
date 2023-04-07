package com.xftxyz.rocketblog.controller;

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
import com.xftxyz.rocketblog.pojo.BlogDetail;
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VComment;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    // 搜索博客
    @GetMapping("/search")
    public PageInfo<BlogInfo> search(@RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize,
            HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.searchBlogs(keyword, user);
        return new PageInfo<>(blogs);
    }

    @PostMapping("/publish")
    public Integer publish(@RequestBody Blog blog, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        int insert = blogService.publish(blog, user);
        return insert;
    }

    @GetMapping("/draft")
    public PageInfo<BlogInfo> draft(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> draftBlogs = blogService.getDraftBlogs(user);
        return new PageInfo<>(draftBlogs);
    }

    // 热门博客，分页返回
    @GetMapping("/hot")
    public PageInfo<BlogInfo> hot(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getHotBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    // 点赞最多
    @GetMapping("/hot/like")
    public PageInfo<BlogInfo> hotLike(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getMostLikeBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    // 收藏最多
    @GetMapping("/hot/collect")
    public PageInfo<BlogInfo> hotCollect(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getMostCollectBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    // 评论最多
    @GetMapping("/hot/comment")
    public PageInfo<BlogInfo> hotComment(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getMostCommentBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    // 最新发布，分页返回
    @GetMapping("/new")
    public PageInfo<BlogInfo> newBlog(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> newBlogs = blogService.getNewBlogs(user);
        return new PageInfo<>(newBlogs);
    }

    // 获取我关注的人的博客
    @GetMapping("/follows")
    public PageInfo<BlogInfo> getFollows(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> follows = blogService.getFollowsBlogs(user.getUserid());
        return new PageInfo<>(follows);
    }

    // 获取博客信息
    @GetMapping("/info/{blogId}")
    public BlogInfo info(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        BlogInfo blogInfo = blogService.getBlogInfo(blogId, user);
        return blogInfo;
    }

    // 获取博客详情
    @GetMapping("/detail/{blogId}")
    public BlogDetail detail(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        BlogDetail blog = blogService.getBlogDetail(blogId, user);
        return blog;
    }

    // 修改
    @PutMapping("/update")
    public Integer update(@RequestBody Blog blog, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        // blog.setUserid(user.getUserid());
        int update = blogService.updateBlog(blog, user);
        return update;
    }

    // 删除
    @DeleteMapping("/delete/{blogId}")
    public Integer delete(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        int delete = blogService.remove(blogId, user.getUserid());
        return delete;
    }

    // 我发布的博客
    @GetMapping("/my")
    public PageInfo<BlogInfo> my(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> myBlogs = blogService.getMyBlogs(user.getUserid());
        return new PageInfo<>(myBlogs);
    }

    // 收藏
    @GetMapping("/collect/{blogId}")
    public Long collect(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        long collect = blogService.collect(user.getUserid(), blogId);
        return collect;
    }

    // 取消收藏
    @DeleteMapping("/collect/{blogId}")
    public Long cancelCollect(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        long cancelCollect = blogService.cancelCollect(user.getUserid(), blogId);
        return cancelCollect;
    }

    // 获取收藏的博客
    @GetMapping("/collects")
    public PageInfo<BlogInfo> getCollects(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> collects = blogService.getCollectsBlogs(user.getUserid());
        return new PageInfo<>(collects);
    }

    // 点赞
    @GetMapping("/like/{blogId}")
    public Long like(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        long like = blogService.like(user.getUserid(), blogId);
        return like;
    }

    // 取消点赞
    @DeleteMapping("/like/{blogId}")
    public Long cancelLike(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        long cancelLike = blogService.cancelLike(user.getUserid(), blogId);
        return cancelLike;
    }

    // 获取点赞的博客
    @GetMapping("/likes")
    public PageInfo<BlogInfo> getLikes(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> likes = blogService.getLikesBlogs(user.getUserid());
        return new PageInfo<>(likes);
    }

    // 评论
    @PostMapping("/comment")
    public VComment comment(@RequestBody Comment comment, HttpSession session) {
        User user = (User) Utils.currentUser(session);
        comment.setUserid(user.getUserid());
        blogService.addComment(comment);
        VComment commentDetail = blogService.getCommentDetail(comment);
        return commentDetail;
    }

    // 删除评论
    @DeleteMapping("/comment/{commentId}")
    public Integer deleteComment(@PathVariable("commentId") Long commentId, HttpSession session) {
        int delete = blogService.deleteComment(commentId);
        return delete;
    }

    // 获取评论，分页，默认第一页，每页5条
    @GetMapping("/comment/{blogId}")
    public PageInfo<VComment> getComments(@PathVariable("blogId") Long blogId,
            @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<VComment> comments = blogService.getCommentsByBlogId(blogId);
        return new PageInfo<>(comments);
    }

}
