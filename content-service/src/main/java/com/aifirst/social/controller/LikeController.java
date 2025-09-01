package com.aifirst.social.controller;

import com.aifirst.social.model.Like;
import com.aifirst.social.repository.LikeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeRepository likeRepository;

    public LikeController(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @PostMapping("/{contentId}")
    public ResponseEntity<?> likeContent(@PathVariable Long contentId) {
        // FIXME: Get user ID from security context
        long userId = 1L; // Placeholder

        Like like = new Like(userId, contentId);
        likeRepository.save(like);

        return ResponseEntity.ok("Content liked successfully");
    }
}
