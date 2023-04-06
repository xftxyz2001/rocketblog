package com.xftxyz.rocketblog.service;

import java.util.List;

import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.BlogDetail;
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VComment;

public interface BlogService {
    // add; remove; findById; modify; findByXXX; findXXXList

    // 返回所有博客
    public List<BlogInfo> getAllBlogs();

    // 返回指定标题、内容、用户、状态的博客
    public List<BlogInfo> findByExample(BlogInfo blog);

    // 添加博客
    public int add(Blog blog);

    // 删除博客（管理员）
    public int removeRF(Long blogId);

    // 删除博客（用户）
    public int remove(Long blogId, Long userid);

    // 修改博客
    public int updateBlog(Blog blog);// 管理员

    public int updateBlog(Blog blog, User user);

    public long collect(Long userid, Long blogId);

    public long cancelCollect(Long userid, Long blogId);

    public long like(Long userid, Long blogId);

    public long cancelLike(Long userid, Long blogId);

    public int addComment(Comment comment);

    public int deleteComment(Long commentId);

    // 获取热门博客
    public List<BlogInfo> getHotBlogs(User user);

    // 获取点赞最多的博客
    public List<BlogInfo> getMostLikeBlogs(User user);

    // 获取收藏最多的博客
    public List<BlogInfo> getMostCollectBlogs(User user);

    // 获取评论最多的博客
    public List<BlogInfo> getMostCommentBlogs(User user);

    public List<BlogInfo> getNewBlogs(User user);

    public List<BlogInfo> getCollectsBlogs(Long userid);

    public List<BlogInfo> getLikesBlogs(Long userid);

    public List<BlogInfo> getFollowsBlogs(Long userid);

    public BlogDetail getBlogDetail(Long blogId, User user);

    public List<VComment> getCommentsByBlogId(Long blogId);

    public BlogInfo getBlogInfo(Long blogId, User user);

    public VComment getCommentDetail(Comment comment);

    public List<BlogInfo> getMyBlogs(Long userid);

    public List<BlogInfo> searchBlogs(String keyword, User user);

    public int publish(Blog blog, User user);

    public List<BlogInfo> getDraftBlogs(User user);

}
