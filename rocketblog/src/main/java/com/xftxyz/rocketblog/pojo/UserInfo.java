package com.xftxyz.rocketblog.pojo;

public class UserInfo {
    // username
    private String username;
    // avatar
    private String avatar;
    // followings
    private long followings;
    // followers
    private long followers;
    // blogs
    private long blogs;
    // isFollowed
    private boolean isFollowed;

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

    public long getFollowings() {
        return followings;
    }

    public void setFollowings(long followings) {
        this.followings = followings;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getBlogs() {
        return blogs;
    }

    public void setBlogs(long blogs) {
        this.blogs = blogs;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

}
