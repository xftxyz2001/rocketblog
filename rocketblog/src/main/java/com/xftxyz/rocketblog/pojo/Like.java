package com.xftxyz.rocketblog.pojo;

import java.util.Date;

public class Like {
    /**
     * 此字段对应于数据库列t_like.like_id
     */
    private Long likeId;

    /**
     * 此字段对应于数据库列t_like.blog_id
     */
    private Long blogId;

    /**
     * 此字段对应于数据库列t_like.userid
     */
    private Long userid;

    /**
     * 此字段对应于数据库列t_like.createtime
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_like.like_id
     *
     * @return the value of t_like.like_id
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Long getLikeId() {
        return likeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_like.like_id
     *
     * @param likeId the value for t_like.like_id
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_like.blog_id
     *
     * @return the value of t_like.blog_id
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Long getBlogId() {
        return blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_like.blog_id
     *
     * @param blogId the value for t_like.blog_id
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_like.userid
     *
     * @return the value of t_like.userid
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_like.userid
     *
     * @param userid the value for t_like.userid
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_like.createtime
     *
     * @return the value of t_like.createtime
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_like.createtime
     *
     * @param createtime the value for t_like.createtime
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}