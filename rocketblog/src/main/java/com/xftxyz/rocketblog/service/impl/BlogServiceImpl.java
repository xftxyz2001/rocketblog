package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.mapper.BlogDetailMapper;
import com.xftxyz.rocketblog.mapper.BlogInfoMapper;
import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.BookmarkMapper;
import com.xftxyz.rocketblog.mapper.CommentMapper;
import com.xftxyz.rocketblog.mapper.LikeMapper;
import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.BlogDetail;
import com.xftxyz.rocketblog.pojo.BlogDetailExample;
import com.xftxyz.rocketblog.pojo.BlogExample;
import com.xftxyz.rocketblog.pojo.BlogExample.Criteria;
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.BlogInfoExample;
import com.xftxyz.rocketblog.pojo.Bookmark;
import com.xftxyz.rocketblog.pojo.BookmarkExample;
import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.Like;
import com.xftxyz.rocketblog.pojo.LikeExample;
import com.xftxyz.rocketblog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogInfoMapper blogInfoMapper;

    @Autowired
    BlogDetailMapper blogDetailMapper;

    @Autowired
    BookmarkMapper bookmarkMapper;

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Blog> getBlogs() {
        // List<Blog> blogList = blogMapper.selectByExample(null);
        List<Blog> blogList = blogMapper.selectByExampleWithBLOBs(null);
        return blogList;
    }

    @Override
    public List<Blog> getBlogs(Blog blog) {
        BlogExample exBlog = new BlogExample();
        Criteria criteria = exBlog.createCriteria();
        String blogTitle = blog.getBlogTitle();
        if (blogTitle != null && !blogTitle.equals("")) {
            criteria.andBlogTitleLike("%" + blogTitle + "%");
            // System.out.println("blogTitle: " + blogTitle);
        }
        Long userid = blog.getUserid();
        if (userid != null) {
            criteria.andUseridEqualTo(userid);
            // System.out.println("userid: " + userid);
        }
        Integer blogStatus = blog.getBlogStatus();
        if (blogStatus != null) {
            criteria.andBlogStatusEqualTo(blogStatus);
            // System.out.println("blogStatus: " + blogStatus);
        }
        // exBlog..createCriteria().andBlogTitleLike("%" + blog.getBlogTitle() + "%")
        // .andUseridEqualTo(blog.getUserid())
        // .andBlogStatusEqualTo(blog.getBlogStatus());
        // List<Blog> blogList = blogMapper.selectByExample(exBlog);
        List<Blog> blogList = blogMapper.selectByExampleWithBLOBs(exBlog);
        return blogList;
    }

    @Override
    public int addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int insert = blogMapper.insert(blog);
        return insert;
    }

    @Override
    public int deleteBlog(Long blogId) {
        int delete = blogMapper.deleteByPrimaryKey(blogId);
        return delete;
    }

    @Override
    public int updateBlog(Blog blog) {
        int update = blogMapper.updateByPrimaryKey(blog);
        return update;
    }

    @Override
    public int collect(Long userid, Long blogId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserid(userid);
        bookmark.setBlogId(blogId);
        bookmark.setCreatetime(new Date());
        int insert = bookmarkMapper.insert(bookmark);
        return insert;
    }

    @Override
    public int cancelCollect(Long userid, Long blogId) {
        BookmarkExample exBookmark = new BookmarkExample();
        exBookmark.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        int delete = bookmarkMapper.deleteByExample(exBookmark);
        return delete;
    }

    @Override
    public int like(Long userid, Long blogId) {
        Like like = new Like();
        like.setUserid(userid);
        like.setBlogId(blogId);
        like.setCreatetime(new Date());
        int insert = likeMapper.insert(like);
        return insert;
    }

    @Override
    public int cancelLike(Long userid, Long blogId) {
        LikeExample exLike = new LikeExample();
        exLike.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        int delete = likeMapper.deleteByExample(exLike);
        return delete;
    }

    @Override
    public int addComment(Comment comment) {
        comment.setCreatetime(new Date());
        int insert = commentMapper.insert(comment);
        return insert;
    }

    @Override
    public int deleteComment(Long commentId) {
        int delete = commentMapper.deleteByPrimaryKey(commentId);
        return delete;
    }

    @Override
    public List<BlogInfo> getHotBlogs() {
        List<BlogInfo> hotBlogs = blogInfoMapper.selectHotBlogs();
        return hotBlogs;
    }

    @Override
    public List<BlogInfo> getMostLikeBlogs() {
        List<BlogInfo> mostLikeBlogs = blogInfoMapper.selectMostLikeBlogs();
        return mostLikeBlogs;
    }

    @Override
    public List<BlogInfo> getMostCollectBlogs() {
        List<BlogInfo> mostCollectBlogs = blogInfoMapper.selectMostCollectBlogs();
        return mostCollectBlogs;
    }

    @Override
    public List<BlogInfo> getMostCommentBlogs() {
        List<BlogInfo> mostCommentBlogs = blogInfoMapper.selectMostCommentBlogs();
        return mostCommentBlogs;
    }

    @Override
    public List<BlogInfo> getNewBlogs() {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.setOrderByClause("update_time desc");
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        return blogList;
    }

    @Override
    public List<BlogInfo> getCollectsBlogs(Long userid) {
        List<BlogInfo> collects = blogInfoMapper.selectCollectBlogs(userid);
        return collects;
    }

    @Override
    public List<BlogInfo> getLikesBlogs(Long userid) {
        List<BlogInfo> likes = blogInfoMapper.selectLikeBlogs(userid);
        return likes;
    }

    @Override
    public List<BlogInfo> getFollowsBlogs(Long userid) {
        List<BlogInfo> followsBlogs = blogInfoMapper.selectFollowBlogs(userid);
        return followsBlogs;
    }

    @Override
    public BlogDetail getBlogDetail(Long blogId) {
        BlogDetailExample exBlogDetail = new BlogDetailExample();
        exBlogDetail.createCriteria().andBlogIdEqualTo(blogId);
        List<BlogDetail> selectByExample = blogDetailMapper.selectByExample(exBlogDetail);
        BlogDetail blogDetail = selectByExample.get(0);
        return blogDetail;
    }

}
