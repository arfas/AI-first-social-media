package com.aifirst.social.model;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
@IdClass(FollowId.class)
public class Follow {

    @Id
    private Long followerId;

    @Id
    private Long followedId;

    public Follow() {
    }

    public Follow(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

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
