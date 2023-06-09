package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.blog.BlogNotExistException;
import com.xftxyz.rocketblog.exception.user.AlreadyDoneException;
import com.xftxyz.rocketblog.exception.user.IllegalOperationException;
import com.xftxyz.rocketblog.mapper.BlogDetailMapper;
import com.xftxyz.rocketblog.mapper.BlogInfoMapper;
import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.mapper.BookmarkMapper;
import com.xftxyz.rocketblog.mapper.LikeMapper;
import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.BlogDetail;
import com.xftxyz.rocketblog.pojo.BlogDetailExample;
import com.xftxyz.rocketblog.pojo.BlogInfo;
import com.xftxyz.rocketblog.pojo.BlogInfoExample;
import com.xftxyz.rocketblog.pojo.BlogInfoExample.Criteria;
import com.xftxyz.rocketblog.pojo.Bookmark;
import com.xftxyz.rocketblog.pojo.BookmarkExample;
import com.xftxyz.rocketblog.pojo.Like;
import com.xftxyz.rocketblog.pojo.LikeExample;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.service.BlogService;
import com.xftxyz.rocketblog.status.BlogStatus;
import com.xftxyz.rocketblog.status.RoleStatus;

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

    @Override
    public List<BlogInfo> getAllBlogs() {
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(null);
        return blogList;
    }

    @Override
    public List<BlogInfo> findByExample(BlogInfo blog) {
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
    public Integer add(Blog blog) {
        // 设置创建时间
        blog.setCreateTime(new Date());
        // 设置更新时间
        blog.setUpdateTime(new Date());
        int insert = blogMapper.insert(blog);
        return insert;
    }

    @Override
    public Integer remove(Long blogId, Long userid) {
        // 查询博客是否存在
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        if (blog == null) {
            throw new BlogNotExistException("博客" + blogId + "不存在");
        }
        // 是不是自己的博客？
        if (!blog.getUserid().equals(userid)) {
            throw new IllegalOperationException("不是自己的博客，无法删除");
        }
        // 删除博客
        int delete = blogMapper.deleteByPrimaryKey(blogId);
        return delete;
    }

    @Override
    public Integer updateBlog(Blog blog) {
        return blogMapper.updateByPrimaryKeyWithBLOBs(blog);
    }

    @Override
    public Integer updateBlog(Blog blog, User user) {
        // 查询出原来的blog
        Blog oldBlog = blogMapper.selectByPrimaryKey(blog.getBlogId());
        // 如果没查询出来
        if (oldBlog == null) {
            // 发表
            blog.setBlogId(null);
            return publish(blog, user);
        }
        // 是不是自己的博客？
        if (!oldBlog.getUserid().equals(user.getUserid())) {
            throw new IllegalOperationException("不是自己的博客，无法修改");
        }
        // 将新的blog信息复制到旧的blog中
        oldBlog.setBlogTitle(blog.getBlogTitle());
        oldBlog.setCoverImage(blog.getCoverImage());
        oldBlog.setBlogContent(blog.getBlogContent());

        // 设置更新时间
        oldBlog.setUpdateTime(new Date());
        return updateBlog(oldBlog);
    }

    @Override
    public Long collect(Long userid, Long blogId) {
        // 查询用户是否已经收藏过该博客
        BookmarkExample exBookmarkCheck = new BookmarkExample();
        exBookmarkCheck.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        long countCheck = bookmarkMapper.countByExample(exBookmarkCheck);
        if (countCheck > 0) {
            throw new AlreadyDoneException("已收藏过该博客");
        }
        Bookmark bookmark = new Bookmark();
        bookmark.setUserid(userid);
        bookmark.setBlogId(blogId);
        bookmark.setCreatetime(new Date());
        bookmarkMapper.insert(bookmark);

        // 查询当前博客的收藏数
        BookmarkExample exBookmark = new BookmarkExample();
        exBookmark.createCriteria().andBlogIdEqualTo(blogId);
        long count = bookmarkMapper.countByExample(exBookmark);
        return count;
    }

    @Override
    public Long cancelCollect(Long userid, Long blogId) {
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
        return count;
    }

    @Override
    public Long like(Long userid, Long blogId) {
        // 查询用户是否已经点赞过该博客
        LikeExample exLikeCheck = new LikeExample();
        exLikeCheck.createCriteria().andUseridEqualTo(userid).andBlogIdEqualTo(blogId);
        long countCheck = likeMapper.countByExample(exLikeCheck);

        if (countCheck > 0) {
            throw new AlreadyDoneException("已点赞过该博客");
        }
        Like like = new Like();
        like.setUserid(userid);
        like.setBlogId(blogId);
        like.setCreatetime(new Date());
        likeMapper.insert(like);

        // 查询当前博客的点赞数
        LikeExample exLike = new LikeExample();
        exLike.createCriteria().andBlogIdEqualTo(blogId);
        long count = likeMapper.countByExample(exLike);
        return count;
    }

    @Override
    public Long cancelLike(Long userid, Long blogId) {
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
        return count;
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
        // 尝试获取博客详情
        BlogDetailExample exBlogDetail = new BlogDetailExample();
        exBlogDetail.createCriteria().andBlogIdEqualTo(blogId);
        List<BlogDetail> selectByExample = blogDetailMapper.selectByExampleWithBLOBs(exBlogDetail);

        // 博客不存在
        if (selectByExample == null || selectByExample.size() < 0) {
            throw new BlogNotExistException("博客" + blogId + "不存在");
        }

        BlogDetail blogDetail = selectByExample.get(0);

        // 博客未发布，只有作者和管理员可以查看
        if (blogDetail.getBlogStatus() != BlogStatus.PUBLISH) {
            if (!blogDetail.getUserid().equals(user.getUserid()) && user.getIsSuperuser() != RoleStatus.ADMIN) {
                throw new IllegalOperationException("没有权限查看博客");
            }
        }

        // 当用户未登录或管理员查看时，不展示如下信息（是否点赞、收藏）
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
    public BlogInfo getBlogInfo(Long blogId, User user) {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.createCriteria().andBlogIdEqualTo(blogId);
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        blogEx(blogList, user == null ? null : user.getUserid());
        return blogList.get(0);
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
        exBlog.createCriteria().andBlogTitleLike("%" + keyword + "%").andBlogStatusEqualTo(BlogStatus.PUBLISH);
        exBlog.or().andUsernameLike("%" + keyword + "%");

        List<BlogInfo> searchBlogs = blogInfoMapper.selectByExample(exBlog);
        blogEx(searchBlogs, user == null ? null : user.getUserid());
        return searchBlogs;
    }

    @Override
    public Integer publish(Blog blog, User user) {
        blog.setUserid(user.getUserid());
        if (!StringUtils.hasLength(blog.getBlogTitle())) {
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
        int addBlog = add(blog);
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

    @Override
    public Integer removeRF(Long blogId) {
        int delete = blogMapper.deleteByPrimaryKey(blogId);
        return delete;
    }

    @Override
    public Integer reprint(Long blogId, User user) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        if (blog == null) {
            throw new BlogNotExistException("博客" + blogId + "不存在");
        }
        blog.setBlogId(null);
        if (blog.getBlogStatus() != BlogStatus.PUBLISH) {
            throw new IllegalOperationException("不能转载未发布的博客");
        }
        blog.setBlogTitle("【转载】" + blog.getBlogTitle());
        return publish(blog, user);

    }

    @Override
    public List<BlogInfo> getOthersBlogs(Long userId) {
        BlogInfoExample exBlog = new BlogInfoExample();
        exBlog.createCriteria().andUseridEqualTo(userId).andBlogStatusEqualTo(BlogStatus.PUBLISH);
        exBlog.setOrderByClause("update_time desc");
        List<BlogInfo> blogList = blogInfoMapper.selectByExample(exBlog);
        // blogEx(blogList, null);
        return blogList;
    }

}
