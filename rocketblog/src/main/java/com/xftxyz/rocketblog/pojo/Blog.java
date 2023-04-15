package com.xftxyz.rocketblog.pojo;

import java.util.Date;

import com.xftxyz.rocketblog.validation.ValidationGroups;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class Blog {
    /**
     * 博客id
     * 此字段对应于数据库列t_blog.blog_id
     */
    @NotNull(message = "博客id不能为空", groups = ValidationGroups.BlogUpdate.class)
    @Null(message = "博客id必须为空", groups = ValidationGroups.BlogAdd.class)
    private Long blogId;

    /**
     * 博客标题
     * 此字段对应于数据库列t_blog.blog_title
     */
    private String blogTitle;

    /**
     * 博客作者的用户id
     * 此字段对应于数据库列t_blog.userid
     */
    private Long userid;

    /**
     * 博客状态（0：草稿，1：发布）
     * 此字段对应于数据库列t_blog.blog_status
     */
    private Integer blogStatus;

    /**
     * 博客创建时间
     * 此字段对应于数据库列t_blog.create_time
     */
    private Date createTime;

    /**
     * 博客最后更新时间
     * 此字段对应于数据库列t_blog.update_time
     */
    private Date updateTime;

    /**
     * 博客封面图片url
     * 此字段对应于数据库列t_blog.cover_image
     */
    private String coverImage;

    /**
     * 博客内容
     * 此字段对应于数据库列t_blog.blog_content
     */
    @NotBlank(message = "博客内容不能为空", groups = { ValidationGroups.BlogAdd.class, ValidationGroups.BlogUpdate.class })
    private String blogContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.blog_id
     *
     * @return the value of t_blog.blog_id
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Long getBlogId() {
        return blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.blog_id
     *
     * @param blogId the value for t_blog.blog_id
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.blog_title
     *
     * @return the value of t_blog.blog_title
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public String getBlogTitle() {
        return blogTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.blog_title
     *
     * @param blogTitle the value for t_blog.blog_title
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.userid
     *
     * @return the value of t_blog.userid
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.userid
     *
     * @param userid the value for t_blog.userid
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.blog_status
     *
     * @return the value of t_blog.blog_status
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Integer getBlogStatus() {
        return blogStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.blog_status
     *
     * @param blogStatus the value for t_blog.blog_status
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.create_time
     *
     * @return the value of t_blog.create_time
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.create_time
     *
     * @param createTime the value for t_blog.create_time
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.update_time
     *
     * @return the value of t_blog.update_time
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.update_time
     *
     * @param updateTime the value for t_blog.update_time
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.cover_image
     *
     * @return the value of t_blog.cover_image
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public String getCoverImage() {
        return coverImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.cover_image
     *
     * @param coverImage the value for t_blog.cover_image
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.blog_content
     *
     * @return the value of t_blog.blog_content
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public String getBlogContent() {
        return blogContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.blog_content
     *
     * @param blogContent the value for t_blog.blog_content
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent == null ? null : blogContent.trim();
    }
}