package com.stduy.social.app.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stduy.social.app.application.dto.CreatePostRequest;
import com.stduy.social.app.application.port.out.CreatePostPort;
import com.stduy.social.app.domain.Post;

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
