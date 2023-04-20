package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.blog.BlogNotExistException;
import com.xftxyz.rocketblog.exception.user.AlreadyDoneException;
import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.CommentMapper;
import com.xftxyz.rocketblog.mapper.VCommentMapper;
import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.CommentExample;
import com.xftxyz.rocketblog.pojo.VComment;
import com.xftxyz.rocketblog.pojo.VCommentExample;
import com.xftxyz.rocketblog.service.CommentService;

import jakarta.validation.constraints.Min;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    VCommentMapper vcommentMapper;

    private void checkComment(Comment comment) {
        // 查询博客是否存在
        Blog blog = blogMapper.selectByPrimaryKey(comment.getBlogId());
        if (blog == null) {
            throw new BlogNotExistException("博客" + comment.getBlogId() + "不存在");
        }
        // 限定时间内最后一次评论
        CommentExample exComment = new CommentExample();
        exComment.createCriteria().andBlogIdEqualTo(comment.getBlogId())
                .andUseridEqualTo(comment.getUserid())
                .andCreatetimeGreaterThan(
                        new Date(System.currentTimeMillis() - 1000 * EnvironmentVariables.COMMENT_INTERVAL));
        exComment.setOrderByClause("createtime desc");
        List<Comment> comments = commentMapper.selectByExample(exComment);
        if (comments != null && comments.size() > 0) {
            Comment lastComment = comments.get(0);
            // 计算还剩多少秒可以评论
            long interval = (lastComment.getCreatetime().getTime() + 1000 * EnvironmentVariables.COMMENT_INTERVAL)
                    - System.currentTimeMillis();
            if (interval > 0) {
                throw new AlreadyDoneException("短时间内只能评论一次，距离下次可评论还剩" + interval / 1000 + "秒");
            }
        }

    }

    @Override
    public Integer addComment(Comment comment) {

        // 检查是否可评论
        checkComment(comment);
        comment.setCreatetime(new Date());
        return commentMapper.insert(comment);
    }

    @Override
    public Integer deleteComment(Long commentId) {
        int delete = commentMapper.deleteByPrimaryKey(commentId);
        return delete;
    }

    @Override
    public List<VComment> getCommentsByBlogId(Long blogId) {
        VCommentExample exComment = new VCommentExample();
        exComment.createCriteria().andBlogIdEqualTo(blogId);
        exComment.setOrderByClause("createtime desc");
        List<VComment> comments = vcommentMapper.selectByExample(exComment);
        return comments;
    }

    @Override
    public VComment getCommentDetail(Comment comment) {
        // 使用VCommentMapper查出刚刚插入的评论
        VCommentExample exComment = new VCommentExample();
        exComment.createCriteria().andBlogIdEqualTo(comment.getBlogId()).andUseridEqualTo(comment.getUserid());
        exComment.setOrderByClause("createtime desc");
        // exComment.setOrderByClause("createtime desc limit 1");
        List<VComment> comments = vcommentMapper.selectByExample(exComment);
        if (comments.size() < 1) {
            return null;
        }
        VComment vComment = comments.get(0);
        return vComment;

    }

    @Override
    public List<Comment> getAllComments() {
        CommentExample exComment = new CommentExample();
        exComment.setOrderByClause("createtime desc");
        List<Comment> comments = commentMapper.selectByExample(exComment);
        return comments;
    }

    @Override
    public List<Comment> findCommentsByExample(Comment comment) {
        CommentExample exComment = new CommentExample();
        CommentExample.Criteria criteria = exComment.createCriteria();
        if (comment.getBlogId() != null) {
            criteria.andBlogIdEqualTo(comment.getBlogId());
        }
        if (comment.getUserid() != null) {
            criteria.andUseridEqualTo(comment.getUserid());
        }
        exComment.setOrderByClause("createtime desc");
        List<Comment> comments = commentMapper.selectByExample(exComment);
        return comments;

    }

    @Override
    public Integer removeComment(@Min(value = 1, message = "评论ID不合法") Long commentId) {
        return commentMapper.deleteByPrimaryKey(commentId);
    }

}
