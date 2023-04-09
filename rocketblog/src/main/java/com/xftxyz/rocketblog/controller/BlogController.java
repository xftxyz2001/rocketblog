package com.xftxyz.rocketblog.controller;

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
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VComment;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.HttpSession;

/**
 * 博客相关
 */
@RestController
@Validated
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    /**
     * 搜索博客。
     *
     * @param keyword  关键字
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含搜索结果和分页信息 {@link BlogInfo}
     */
    @GetMapping("/search")
    public PageInfo<BlogInfo> search(@RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize,
            HttpSession session) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> blogs = blogService.searchBlogs(keyword, user);
        return new PageInfo<>(blogs);
    }

    /**
     * 发布博客。
     *
     * @param blog    要发布的博客对象
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个整数值，表示博客插入的行数
     */
    @PostMapping("/publish")
    public Integer publish(@RequestBody Blog blog, HttpSession session) {
        User user = Utils.currentUser(session);
        int insert = blogService.publish(blog, user);
        return insert;
    }

    /**
     * 获取自己博客草稿列表。
     *
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含草稿博客列表和分页信息
     */
    @GetMapping("/draft")
    public PageInfo<BlogInfo> draft(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> draftBlogs = blogService.getDraftBlogs(user);
        return new PageInfo<>(draftBlogs);
    }

    /**
     * 获取热门博客列表。
     *
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含热门博客列表和分页信息
     */
    @GetMapping("/hot")
    public PageInfo<BlogInfo> hot(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getHotBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    /**
     * 获取点赞最多的博客列表。
     *
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含点赞最多的博客列表和分页信息
     */
    @GetMapping("/hot/like")
    public PageInfo<BlogInfo> hotLike(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getMostLikeBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    /**
     * 获取收藏最多的博客列表。
     *
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含收藏最多的博客列表和分页信息
     */
    @GetMapping("/hot/collect")
    public PageInfo<BlogInfo> hotCollect(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getMostCollectBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    /**
     * 获取评论最多的博客列表。
     *
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含评论最多的博客列表和分页信息
     */
    @GetMapping("/hot/comment")
    public PageInfo<BlogInfo> hotComment(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> hotBlogs = blogService.getMostCommentBlogs(user);
        return new PageInfo<>(hotBlogs);
    }

    /**
     * 获取最新发布的博客列表。
     *
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @param session  HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link PageInfo} 对象，包含最新发布的博客列表和分页信息
     */
    @GetMapping("/new")
    public PageInfo<BlogInfo> newBlog(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> newBlogs = blogService.getNewBlogs(user);
        return new PageInfo<>(newBlogs);
    }

    /**
     * 获取我关注的人的博客列表。
     *
     * @param session  HttpSession对象，获取当前登录用户信息
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @return 返回一个 {@link PageInfo} 对象，包含我关注的人的博客列表和分页信息
     */
    @GetMapping("/follows")
    public PageInfo<BlogInfo> getFollows(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> follows = blogService.getFollowsBlogs(user.getUserid());
        return new PageInfo<>(follows);
    }

    /**
     * 获取博客信息。
     *
     * @param blogId  需要获取的博客的id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link BlogInfo} 对象，包含指定博客的信息
     */
    @GetMapping("/info/{blogId}")
    public BlogInfo info(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = Utils.currentUser(session);
        BlogInfo blogInfo = blogService.getBlogInfo(blogId, user);
        return blogInfo;
    }

    /**
     * 获取博客详情。
     *
     * @param blogId  需要获取的博客的id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个 {@link BlogDetail} 对象，包含指定博客的详细信息
     */
    @GetMapping("/detail/{blogId}")
    public BlogDetail detail(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = Utils.currentUser(session);
        BlogDetail blog = blogService.getBlogDetail(blogId, user);
        return blog;
    }

    /**
     * 修改博客。
     *
     * @param blog    需要修改的博客（包含博客id和修改后的内容）
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个整数，表示成功修改的博客数
     */
    @PutMapping("/update")
    public Integer update(@RequestBody Blog blog, HttpSession session) {
        User user = Utils.currentUser(session);
        int update = blogService.updateBlog(blog, user);
        return update;
    }

    /**
     * 删除博客。
     *
     * @param blogId  需要删除的博客的id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个整数，表示成功删除的博客数
     */
    @DeleteMapping("/delete/{blogId}")
    public Integer delete(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = Utils.currentUser(session);
        int delete = blogService.remove(blogId, user.getUserid());
        return delete;
    }

    /**
     * 获取我发布的博客列表。
     *
     * @param session  HttpSession对象，获取当前登录用户信息
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @return 返回一个 {@link PageInfo} 对象，包含我发布的博客列表和分页信息
     */
    @GetMapping("/my")
    public PageInfo<BlogInfo> my(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> myBlogs = blogService.getMyBlogs(user.getUserid());
        return new PageInfo<>(myBlogs);
    }

    /**
     * 收藏博客。
     *
     * @param blogId  博客id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个long类型值，表示收藏博客的数量
     */
    @GetMapping("/collect/{blogId}")
    public Long collect(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = Utils.currentUser(session);
        long collect = blogService.collect(user.getUserid(), blogId);
        return collect;
    }

    /**
     * 取消收藏博客。
     *
     * @param blogId  博客id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回一个long类型值，表示取消收藏博客的数量
     */
    @DeleteMapping("/collect/{blogId}")
    public Long cancelCollect(@PathVariable("blogId") Long blogId, HttpSession session) {
        User user = Utils.currentUser(session);
        long cancelCollect = blogService.cancelCollect(user.getUserid(), blogId);
        return cancelCollect;
    }

    /**
     * 获取我收藏的博客列表。
     *
     * @param session  HttpSession对象，获取当前登录用户信息
     * @param pageNum  分页页码
     * @param pageSize 分页大小
     * @return 返回一个 {@link PageInfo} 对象，包含我收藏的博客列表和分页信息
     */
    @GetMapping("/collects")
    public PageInfo<BlogInfo> getCollects(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> collects = blogService.getCollectsBlogs(user.getUserid());
        return new PageInfo<>(collects);
    }

    /**
     * 点赞博客。
     * 
     * @param blogId  需要点赞的博客id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回点赞的数量
     */
    @GetMapping("/like/{blogId}")
    public Long like(@PathVariable("blogId") Long blogId, HttpSession session) {
        // 获取当前登录用户信息，并进行点赞操作
        User user = Utils.currentUser(session);
        long like = blogService.like(user.getUserid(), blogId);
        return like;
    }

    /**
     * 取消点赞博客。
     * 
     * @param blogId  需要取消点赞的博客id
     * @param session HttpSession对象，获取当前登录用户信息
     * @return 返回点赞的数量
     */
    @DeleteMapping("/like/{blogId}")
    public Long cancelLike(@PathVariable("blogId") Long blogId, HttpSession session) {
        // 获取当前登录用户信息，并进行取消点赞操作
        User user = Utils.currentUser(session);
        long cancelLike = blogService.cancelLike(user.getUserid(), blogId);
        return cancelLike;
    }

    /**
     * 获取当前登录用户点赞的博客列表。
     * 
     * @param session  HttpSession对象，获取当前登录用户信息
     * @param pageNum  当前页数，默认为1
     * @param pageSize 每页显示的数据量，默认为5
     * @return 返回 {@link PageInfo} 对象，包含当前页数、每页数据量和当前登录用户点赞的博客列表
     */
    @GetMapping("/likes")
    public PageInfo<BlogInfo> getLikes(HttpSession session,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        // 获取当前登录用户信息，并查询当前登录用户点赞的博客列表
        User user = Utils.currentUser(session);
        PageHelper.startPage(pageNum, pageSize);
        List<BlogInfo> likes = blogService.getLikesBlogs(user.getUserid());

        // 返回一个 PageInfo 对象，包含当前页数、每页数据量和当前登录用户点赞的博客列表
        return new PageInfo<>(likes);
    }

    /**
     * 对指定博客进行评论。
     *
     * @param comment 包含评论内容和博客 ID 的 {@link Comment} 对象
     * @param session HttpSession 对象，获取当前登录用户信息
     * @return 返回一个 {@link VComment} 对象，表示评论的详细信息
     */
    @PostMapping("/comment")
    public VComment comment(@RequestBody Comment comment, HttpSession session) {
        User user = Utils.currentUser(session);
        comment.setUserid(user.getUserid());
        blogService.addComment(comment);
        VComment commentDetail = blogService.getCommentDetail(comment);
        return commentDetail;
    }

    /**
     * 删除指定评论。
     *
     * @param commentId 评论 ID
     * @param session   HttpSession 对象，获取当前登录用户信息
     * @return 返回一个 Integer 类型的值，表示删除评论的数量
     */
    @DeleteMapping("/comment/{commentId}")
    public Integer deleteComment(@PathVariable("commentId") Long commentId, HttpSession session) {
        int delete = blogService.deleteComment(commentId);
        return delete;
    }

    /**
     * 获取指定博客的评论列表。
     *
     * @param blogId   博客 ID
     * @param pageNum  分页页码，默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @return 返回一个 {@link PageInfo} 对象，表示指定博客的评论列表
     */
    @GetMapping("/comment/{blogId}")
    public PageInfo<VComment> getComments(@PathVariable("blogId") Long blogId,
            @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = EnvironmentVariables.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<VComment> comments = blogService.getCommentsByBlogId(blogId);
        return new PageInfo<>(comments);
    }

}
