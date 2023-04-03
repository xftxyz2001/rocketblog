package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.BlogInfoExample;
import com.xftxyz.rocketblog.pojo.BlogInfoExample.Criteria;
import com.xftxyz.rocketblog.pojo.Bookmark;
import com.xftxyz.rocketblog.pojo.BookmarkExample;
import com.xftxyz.rocketblog.pojo.Comment;
import com.xftxyz.rocketblog.pojo.Like;
import com.xftxyz.rocketblog.pojo.LikeExample;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.pojo.VComment;
import com.xftxyz.rocketblog.pojo.VCommentExample;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.status.BlogStatus;

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
    public List<BlogInfo> getBlogs() {
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(null);
        return blogList;
    }

    @Override
    public List<BlogInfo> getBlogs(BlogInfo blog) {
        BlogInfoExample exBlog = new BlogInfoExample();
        Criteria criteria = exBlog.createCriteria();
        String blogTitle = blog.getBlogTitle();
        if (blogTitle != null && !blogTitle.equals("")) {
            criteria.andBlogTitleLike("%" + blogTitle + "%");
        }
        Long userid = blog.getUserid();
        if (userid != null) {
            criteria.andUseridEqualTo(userid);
        }
        Integer blogStatus = blog.getBlogStatus();
        if (blogStatus != null) {
            criteria.andBlogStatusEqualTo(blogStatus);
        }
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        return blogList;
    }

    @Override
    public int addBlog(Blog blog) {
        // 设置创建时间
        blog.setCreateTime(new Date());
        // 设置更新时间
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
    public int updateBlog(Blog blog, User user) {
        // 是不是自己的博客？
        if (blog.getUserid() != user.getUserid()) {
            return -1;
        }
        // 设置更新时间
        blog.setUpdateTime(new Date());
        return updateBlog(blog);
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
        exBlog.createCriteria().andBlogStatusEqualTo(BlogStatus.PUBLISH);
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

    @Override
    public List<BlogInfo> searchBlogs(String keyword, User user) {
        BlogInfoExample exBlog = new BlogInfoExample();
        // blogTitle or username
        exBlog.createCriteria().andBlogTitleLike("%" + keyword + "%");
        exBlog.or().andUsernameLike("%" + keyword + "%");

        List<BlogInfo> searchBlogs = blogInfoMapper.selectByExample(exBlog);
        blogEx(searchBlogs, user == null ? null : user.getUserid());
        return searchBlogs;
    }

    @Override
    public int publish(Blog blog, User user) {
        // 博客内容为空
        // if (!StringUtils.hasLength(blog.getBlogContent())) {
        // return -1;
        // }

        blog.setUserid(user.getUserid());
        if (blog.getBlogTitle() == null || blog.getBlogTitle().equals("")) {
            blog.setBlogTitle("无标题");
        }
        // 如果不是草稿
        if (blog.getBlogStatus() != BlogStatus.DRAFT) {
            // 设置属性为发布
            blog.setBlogStatus(BlogStatus.PUBLISH);
        }
        // 如果没有设置coverImage
        if (!StringUtils.hasLength(blog.getCoverImage())) {
            // StringUtils.hasText()和StringUtils.hasLength()的区别
            // hasText()方法判断字符串是否有内容，有内容返回true，没有内容返回false
            // hasLength()方法判断字符串是否有长度，有长度返回true，没有长度返回false
            blog.setCoverImage(user.getAvatar());
        }
        // 添加
        int addBlog = addBlog(blog);
        return addBlog;
    }

    @Override
    public List<BlogInfo> getDraftBlogs(User user) {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.createCriteria().andUseridEqualTo(user.getUserid()).andBlogStatusEqualTo(BlogStatus.DRAFT);
        exBlog.setOrderByClause("update_time desc");
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        blogEx(blogList, user.getUserid());
        return blogList;
    }

}
