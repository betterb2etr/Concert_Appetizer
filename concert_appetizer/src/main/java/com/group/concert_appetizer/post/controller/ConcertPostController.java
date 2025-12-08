package com.group.concert_appetizer.post.controller;

import com.group.concert_appetizer.post.dto.CreatePostRequest;
import com.group.concert_appetizer.post.dto.PostResponse;
import com.group.concert_appetizer.post.service.ConcertPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class ConcertPostController {

    private final ConcertPostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll() {
        return ResponseEntity.ok().body(postService.getAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> get(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.get(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> update(
            @PathVariable Long postId,
            @Valid @RequestBody CreatePostRequest request) {

        return ResponseEntity.ok().body(postService.update(postId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok(null);
    }
}

