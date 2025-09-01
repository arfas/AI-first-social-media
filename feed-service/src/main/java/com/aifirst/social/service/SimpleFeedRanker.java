package com.aifirst.social.service;

import com.aifirst.social.dto.Follow;
import com.aifirst.social.dto.Post;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SimpleFeedRanker implements FeedRanker {

    private final UserServiceClient userServiceClient;

    public SimpleFeedRanker(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public List<Post> rank(Long userId, List<Post> posts) {
        List<Follow> following = userServiceClient.getFollowing(userId);
        Set<Long> followedUserIds = following.stream()
                .map(Follow::getFollowedId)
                .collect(Collectors.toSet());

        // Separate posts from followed users and others
        List<Post> followedPosts = posts.stream()
                .filter(p -> followedUserIds.contains(p.getUserId()))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());

        List<Post> otherPosts = posts.stream()
                .filter(p -> !followedUserIds.contains(p.getUserId()))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());

        // Combine the lists, with followed users' posts first
        followedPosts.addAll(otherPosts);

        // Random shuffle within the followed posts
        Collections.shuffle(followedPosts);

        // Return top 10
        return followedPosts.stream().limit(10).collect(Collectors.toList());
    }
}
