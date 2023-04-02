package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.mapper.BlogDetailMapper;
import com.xftxyz.rocketblog.mapper.BlogInfoMapper;
import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.BookmarkMapper;
import com.xftxyz.rocketblog.mapper.CommentMapper;
import com.xftxyz.rocketblog.mapper.LikeMapper;
import com.xftxyz.rocketblog.mapper.VCommentMapper;
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
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VComment;
import com.xftxyz.rocketblog.pojo.VCommentExample;
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

    @Autowired
    VCommentMapper vcommentMapper;

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
    public Map<String, Object> collect(Long userid, Long blogId) {
        Map<String, Object> map = new HashMap<>();
        // 查询用户是否已经收藏过该博客
        BookmarkExample exBookmarkCheck = new BookmarkExample();
        exBookmarkCheck.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        long countCheck = bookmarkMapper.countByExample(exBookmarkCheck);

        if (countCheck < 1) {
            Bookmark bookmark = new Bookmark();
            bookmark.setUserid(userid);
            bookmark.setBlogId(blogId);
            bookmark.setCreatetime(new Date());
            bookmarkMapper.insert(bookmark);
        }

        // 查询当前博客的收藏数
        BookmarkExample exBookmark = new BookmarkExample();
        exBookmark.createCriteria().andBlogIdEqualTo(blogId);
        long count = bookmarkMapper.countByExample(exBookmark);

        map.put("msg", countCheck < 1 ? "收藏成功" : "已收藏过该博客");
        map.put("count", count);
        return map;
    }

    @Override
    public Map<String, Object> cancelCollect(Long userid, Long blogId) {
        Map<String, Object> map = new HashMap<>();
        // 查询用户是否已经收藏过该博客
        BookmarkExample exBookmarkCheck = new BookmarkExample();
        exBookmarkCheck.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        long countCheck = bookmarkMapper.countByExample(exBookmarkCheck);

        if (countCheck > 0) {
            BookmarkExample exBookmark = new BookmarkExample();
            exBookmark.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
            bookmarkMapper.deleteByExample(exBookmark);
        }

        // 查询当前博客的收藏数
        BookmarkExample exBookmarkNew = new BookmarkExample();
        exBookmarkNew.createCriteria().andBlogIdEqualTo(blogId);
        long count = bookmarkMapper.countByExample(exBookmarkNew);

        map.put("msg", countCheck > 0 ? "取消收藏成功" : "未收藏过该博客");
        map.put("count", count);
        return map;
    }

    @Override
    public Map<String, Object> like(Long userid, Long blogId) {
        Map<String, Object> map = new HashMap<>();
        // 查询用户是否已经点赞过该博客
        LikeExample exLikeCheck = new LikeExample();
        exLikeCheck.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        long countCheck = likeMapper.countByExample(exLikeCheck);

        if (countCheck < 1) {
            Like like = new Like();
            like.setUserid(userid);
            like.setBlogId(blogId);
            like.setCreatetime(new Date());
            likeMapper.insert(like);
        }

        // 查询当前博客的点赞数
        LikeExample exLike = new LikeExample();
        exLike.createCriteria().andBlogIdEqualTo(blogId);
        long count = likeMapper.countByExample(exLike);

        map.put("msg", countCheck < 1 ? "点赞成功" : "已点赞过该博客");
        map.put("count", count);
        return map;
    }

    @Override
    public Map<String, Object> cancelLike(Long userid, Long blogId) {
        Map<String, Object> map = new HashMap<>();
        // 查询用户是否已经点赞过该博客
        LikeExample exLikeCheck = new LikeExample();
        exLikeCheck.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        long countCheck = likeMapper.countByExample(exLikeCheck);
        if (countCheck > 0) {
            LikeExample exLike = new LikeExample();
            exLike.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
            likeMapper.deleteByExample(exLike);
        }

        // 查询当前博客的点赞数
        LikeExample exLikeNew = new LikeExample();
        exLikeNew.createCriteria().andBlogIdEqualTo(blogId);
        long count = likeMapper.countByExample(exLikeNew);

        map.put("msg", countCheck > 0 ? "取消点赞成功" : "未点赞过该博客");
        map.put("count", count);
        return map;
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

    private int blogEx(List<BlogInfo> blogs, Long userid) {
        if (userid == null) {
            return 0;
        }
        int count = 0;
        for (BlogInfo blog : blogs) {
            LikeExample exLike = new LikeExample();
            exLike.createCriteria().andBlogIdEqualTo(blog.getBlogId()).andUseridEqualTo(userid);
            long likeCount = likeMapper.countByExample(exLike);
            blog.setLike(likeCount > 0);
            BookmarkExample exBookmark = new BookmarkExample();
            exBookmark.createCriteria().andBlogIdEqualTo(blog.getBlogId()).andUseridEqualTo(userid);
            long bookmarkCount = bookmarkMapper.countByExample(exBookmark);
            blog.setCollect(bookmarkCount > 0);
            count++;
        }
        return count;
    }

    @Override
    public List<BlogInfo> getHotBlogs(User user) {
        List<BlogInfo> hotBlogs = blogInfoMapper.selectHotBlogs();
        blogEx(hotBlogs, user == null ? null : user.getUserid());
        return hotBlogs;
    }

    @Override
    public List<BlogInfo> getMostLikeBlogs(User user) {
        List<BlogInfo> mostLikeBlogs = blogInfoMapper.selectMostLikeBlogs();
        blogEx(mostLikeBlogs, user == null ? null : user.getUserid());
        return mostLikeBlogs;
    }

    @Override
    public List<BlogInfo> getMostCollectBlogs(User user) {
        List<BlogInfo> mostCollectBlogs = blogInfoMapper.selectMostCollectBlogs();
        blogEx(mostCollectBlogs, user == null ? null : user.getUserid());
        return mostCollectBlogs;
    }

    @Override
    public List<BlogInfo> getMostCommentBlogs(User user) {
        List<BlogInfo> mostCommentBlogs = blogInfoMapper.selectMostCommentBlogs();
        blogEx(mostCommentBlogs, user == null ? null : user.getUserid());
        return mostCommentBlogs;
    }

    @Override
    public List<BlogInfo> getNewBlogs(User user) {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.setOrderByClause("update_time desc");
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        blogEx(blogList, user == null ? null : user.getUserid());
        return blogList;
    }

    @Override
    public List<BlogInfo> getCollectsBlogs(Long userid) {
        List<BlogInfo> collects = blogInfoMapper.selectCollectBlogs(userid);
        blogEx(collects, userid);
        return collects;
    }

    @Override
    public List<BlogInfo> getLikesBlogs(Long userid) {
        List<BlogInfo> likes = blogInfoMapper.selectLikeBlogs(userid);
        blogEx(likes, userid);
        return likes;
    }

    @Override
    public List<BlogInfo> getFollowsBlogs(Long userid) {
        List<BlogInfo> followsBlogs = blogInfoMapper.selectFollowBlogs(userid);
        blogEx(followsBlogs, userid);
        return followsBlogs;
    }

    @Override
    public BlogDetail getBlogDetail(Long blogId, User user) {
        BlogDetailExample exBlogDetail = new BlogDetailExample();
        exBlogDetail.createCriteria().andBlogIdEqualTo(blogId);
        List<BlogDetail> selectByExample = blogDetailMapper.selectByExampleWithBLOBs(exBlogDetail);
        BlogDetail blogDetail = selectByExample.get(0);
        if (user != null) {
            LikeExample exLike = new LikeExample();
            exLike.createCriteria().andBlogIdEqualTo(blogId).andUseridEqualTo(user.getUserid());
            long likeCount = likeMapper.countByExample(exLike);
            blogDetail.setLike(likeCount > 0);
            BookmarkExample exBookmark = new BookmarkExample();
            exBookmark.createCriteria().andBlogIdEqualTo(blogId).andUseridEqualTo(user.getUserid());
            long bookmarkCount = bookmarkMapper.countByExample(exBookmark);
            blogDetail.setCollect(bookmarkCount > 0);
        }

        return blogDetail;
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
    public BlogInfo getBlogInfo(Long blogId, User user) {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.createCriteria().andBlogIdEqualTo(blogId);
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        blogEx(blogList, user == null ? null : user.getUserid());
        return blogList.get(0);
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
    public List<BlogInfo> getMyBlogs(Long userid) {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.createCriteria().andUseridEqualTo(userid);
        exBlog.setOrderByClause("update_time desc");
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        blogEx(blogList, userid);
        return blogList;
    }

}
