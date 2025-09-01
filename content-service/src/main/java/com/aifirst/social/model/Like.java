package com.aifirst.social.model;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
@IdClass(LikeId.class)
public class Like {

    @Id
    private Long userId;

    @Id
    private Long contentId;

    public Like() {
    }

    public Like(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}
