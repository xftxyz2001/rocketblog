package com.xftxyz.rocketblog.pojo;

import java.util.Date;

public class BlogInfo {

    /**
     * 用户是否点赞该博客
     */
    private Boolean isLike;

    /**
     * 用户是否收藏该博客
     */
    private Boolean isCollect;

    public Boolean isLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public Boolean isCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    /**
     * 博客id
     * 此字段对应于数据库列v_bloginfo.blog_id
     */
    private Long blogId;

    /**
     * 博客标题
     * 此字段对应于数据库列v_bloginfo.blog_title
     */
    private String blogTitle;

    /**
     * 博客摘要100字
     * 此字段对应于数据库列v_bloginfo.blog_summary
     */
    private String blogSummary;

    /**
     * 博客作者的用户id
     * 此字段对应于数据库列v_bloginfo.userid
     */
    private Long userid;

    /**
     * 博客作者的用户名
     * 此字段对应于数据库列v_bloginfo.username
     */
    private String username;

    /**
     * 博客作者的头像
     * 此字段对应于数据库列v_bloginfo.avatar
     */
    private String avatar;

    /**
     * 博客状态（0：草稿，1：发布）
     * 此字段对应于数据库列v_bloginfo.blog_status
     */
    private Integer blogStatus;

    /**
     * 博客创建时间
     * 此字段对应于数据库列v_bloginfo.create_time
     */
    private Date createTime;

    /**
     * 博客最后更新时间
     * 此字段对应于数据库列v_bloginfo.update_time
     */
    private Date updateTime;

    /**
     * 博客封面图片url
     * 此字段对应于数据库列v_bloginfo.cover_image
     */
    private String coverImage;

    /**
     * 博客点赞数
     * 此字段对应于数据库列v_bloginfo.like_count
     */
    private Long likeCount;

    /**
     * 博客评论数
     * 此字段对应于数据库列v_bloginfo.comment_count
     */
    private Long commentCount;

    /**
     * 博客收藏数
     * 此字段对应于数据库列v_bloginfo.bookmark_count
     */
    private Long bookmarkCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.blog_id
     *
     * @return the value of v_bloginfo.blog_id
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Long getBlogId() {
        return blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.blog_id
     *
     * @param blogId the value for v_bloginfo.blog_id
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.blog_title
     *
     * @return the value of v_bloginfo.blog_title
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public String getBlogTitle() {
        return blogTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.blog_title
     *
     * @param blogTitle the value for v_bloginfo.blog_title
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.blog_summary
     *
     * @return the value of v_bloginfo.blog_summary
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public String getBlogSummary() {
        return blogSummary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.blog_summary
     *
     * @param blogSummary the value for v_bloginfo.blog_summary
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary == null ? null : blogSummary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.userid
     *
     * @return the value of v_bloginfo.userid
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.userid
     *
     * @param userid the value for v_bloginfo.userid
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.username
     *
     * @return the value of v_bloginfo.username
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.username
     *
     * @param username the value for v_bloginfo.username
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.avatar
     *
     * @return the value of v_bloginfo.avatar
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.avatar
     *
     * @param avatar the value for v_bloginfo.avatar
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.blog_status
     *
     * @return the value of v_bloginfo.blog_status
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Integer getBlogStatus() {
        return blogStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.blog_status
     *
     * @param blogStatus the value for v_bloginfo.blog_status
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.create_time
     *
     * @return the value of v_bloginfo.create_time
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.create_time
     *
     * @param createTime the value for v_bloginfo.create_time
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.update_time
     *
     * @return the value of v_bloginfo.update_time
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.update_time
     *
     * @param updateTime the value for v_bloginfo.update_time
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.cover_image
     *
     * @return the value of v_bloginfo.cover_image
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public String getCoverImage() {
        return coverImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.cover_image
     *
     * @param coverImage the value for v_bloginfo.cover_image
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.like_count
     *
     * @return the value of v_bloginfo.like_count
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.like_count
     *
     * @param likeCount the value for v_bloginfo.like_count
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_bloginfo.comment_count
     *
     * @return the value of v_bloginfo.comment_count
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.comment_count
     *
     * @param commentCount the value for v_bloginfo.comment_count
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column
     * v_bloginfo.bookmark_count
     *
     * @return the value of v_bloginfo.bookmark_count
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public Long getBookmarkCount() {
        return bookmarkCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_bloginfo.bookmark_count
     *
     * @param bookmarkCount the value for v_bloginfo.bookmark_count
     *
     * @mbg.generated Sat Apr 01 15:13:18 CST 2023
     */
    public void setBookmarkCount(Long bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }
}