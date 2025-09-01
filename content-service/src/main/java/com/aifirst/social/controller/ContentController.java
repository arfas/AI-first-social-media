package com.aifirst.social.controller;

import com.aifirst.social.model.Content;
import com.aifirst.social.model.ContentType;
import com.aifirst.social.repository.ContentRepository;
import com.aifirst.social.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final StorageService storageService;
    private final ContentRepository contentRepository;

    public ContentController(StorageService storageService, ContentRepository contentRepository) {
        this.storageService = storageService;
        this.contentRepository = contentRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadContent(@RequestParam("file") MultipartFile file,
                                           @RequestParam("caption") String caption,
                                           @RequestParam("type") ContentType type) {
        // FIXME: Get user ID from security context once authentication is integrated
        long userId = 1L; // Placeholder

        String fileUrl = null;
        if (type == ContentType.IMAGE || type == ContentType.VIDEO) {
            fileUrl = storageService.store(file);
        }

        Content content = new Content();
        content.setUserId(userId);
        content.setType(type);
        content.setUrl(fileUrl);
        content.setCaption(caption);

        contentRepository.save(content);

        return ResponseEntity.ok("Content uploaded successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable Long id) {
        return contentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<java.util.List<Content>> getAllContent() {
        return ResponseEntity.ok(contentRepository.findAll());
    }
}
