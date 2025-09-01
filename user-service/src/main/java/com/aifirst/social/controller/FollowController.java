package com.aifirst.social.controller;

import com.aifirst.social.model.Follow;
import com.aifirst.social.repository.FollowRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowRepository followRepository;

    public FollowController(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> followUser(@PathVariable Long userId, Principal principal) {
        // In a real app, get the current user's ID from the Principal
        // long followerId = Long.parseLong(principal.getName());
        // For now, we'll use a placeholder
        long followerId = 1L; // Placeholder for the current user

        Follow follow = new Follow(followerId, userId);
        followRepository.save(follow);

        return ResponseEntity.ok("Successfully followed user");
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Follow>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followRepository.findByFollowerId(userId));
    }
}
