package com.aifirst.social.controller;

import com.aifirst.social.model.Comment;
import com.aifirst.social.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/{contentId}")
    public ResponseEntity<?> addComment(@PathVariable Long contentId, @RequestBody String text) {
        // FIXME: Get user ID from security context
        long userId = 1L; // Placeholder

        Comment comment = new Comment();
        comment.setContentId(contentId);
        comment.setUserId(userId);
        comment.setText(text);

        commentRepository.save(comment);

        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long contentId) {
        return ResponseEntity.ok(commentRepository.findByContentId(contentId));
    }
}
