package com.xftxyz.rocketblog.service;

import java.util.List;

import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.VComment;

public interface CommentService {

    public Integer addComment(Comment comment);

    public Integer deleteComment(Long commentId);

    public List<VComment> getCommentsByBlogId(Long blogId);

    public VComment getCommentDetail(Comment comment);

    public List<Comment> getAllComments();

    public List<Comment> findCommentsByExample(Comment comment);

    public Integer removeComment(Long commentId);

}
