package com.stduy.social.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stduy.social.application.dto.CreatePostRequest;
import com.stduy.social.application.port.out.CreatePostPort;
import com.stduy.social.domain.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {
    private final CreatePostPort createPostPort;

    @Transactional
    public Long createPost(CreatePostRequest request) {
        Post post = request.toEntity();
        return createPostPort.createPost(post);
    }
}
