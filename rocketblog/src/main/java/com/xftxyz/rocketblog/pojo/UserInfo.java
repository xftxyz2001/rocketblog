package com.xftxyz.rocketblog.pojo;

public class UserInfo {
    /**
     * 用户id
     */
    private Long userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 关注数
     */
    private Long followings;
    /**
     * 粉丝数
     */
    private Long followers;
    /**
     * 文章数
     */
    private Long blogs;
    /**
     * 是否关注
     */
    private Boolean isFollowed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getFollowings() {
        return followings;
    }

    public void setFollowings(Long followings) {
        this.followings = followings;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getBlogs() {
        return blogs;
    }

    public void setBlogs(Long blogs) {
        this.blogs = blogs;
    }

    public Boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(Boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

}
