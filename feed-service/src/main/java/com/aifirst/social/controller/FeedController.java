package com.aifirst.social.controller;

import com.aifirst.social.dto.Post;
import com.aifirst.social.service.ContentServiceClient;
import com.aifirst.social.service.FeedRanker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final ContentServiceClient contentServiceClient;
    private final FeedRanker feedRanker;

    public FeedController(ContentServiceClient contentServiceClient, FeedRanker feedRanker) {
        this.contentServiceClient = contentServiceClient;
        this.feedRanker = feedRanker;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getFeed(@PathVariable Long userId, @RequestHeader("Authorization") String authHeader) {
        List<Post> allPosts = contentServiceClient.getAllContent(authHeader);
        List<Post> rankedPosts = feedRanker.rank(userId, allPosts, authHeader);
        return ResponseEntity.ok(rankedPosts);
    }
}
