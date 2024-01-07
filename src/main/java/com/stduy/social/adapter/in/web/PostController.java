package com.stduy.social.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stduy.social.application.command.CreatePostUseCase;
import com.stduy.social.application.dto.CreatePostRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final CreatePostUseCase createPostUseCase;

    @PostMapping("/api/posts")
    public ResponseEntity<Long> createPost(@RequestBody CreatePostRequest request) {
        Long postId = createPostUseCase.createPost(request);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }
}
