package com.xftxyz.rocketblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xftxyz.rocketblog.mapper.BlogMapper;
import com.xftxyz.rocketblog.pojo.Blog;
import com.xftxyz.rocketblog.pojo.BlogExample;
import com.xftxyz.rocketblog.pojo.BlogExample.Criteria;
import com.xftxyz.rocketblog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

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

}
