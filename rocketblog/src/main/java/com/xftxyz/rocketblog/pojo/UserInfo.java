package com.xftxyz.rocketblog.pojo;

public class UserInfo {
    // id
    private Long userid;
    // username
    private String username;
    // avatar
    private String avatar;
    // followings
    private Long followings;
    // followers
    private Long followers;
    // blogs
    private Long blogs;
    // isFollowed
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
