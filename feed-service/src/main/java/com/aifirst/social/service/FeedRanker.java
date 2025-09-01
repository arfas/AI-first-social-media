package com.aifirst.social.service;

import com.aifirst.social.dto.Post;

import java.util.List;

public interface FeedRanker {
    List<Post> rank(Long userId, List<Post> posts);
}
