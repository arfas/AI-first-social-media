package com.aifirst.social.model;

import java.io.Serializable;
import java.util.Objects;

public class LikeId implements Serializable {
    private Long userId;
    private Long contentId;

    public LikeId() {
    }

    public LikeId(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeId likeId = (LikeId) o;
        return Objects.equals(userId, likeId.userId) &&
                Objects.equals(contentId, likeId.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contentId);
    }
}
