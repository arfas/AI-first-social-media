package com.aifirst.social.dto;

public class Follow {
    private Long followerId;
    private Long followedId;

    // Getters and setters
    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }
}
