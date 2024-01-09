package com.stduy.social.application.command;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stduy.social.application.dto.CreatePostRequest;
import com.stduy.social.application.port.out.CreatePostPort;
import com.stduy.social.application.port.out.RecordEventPort;
import com.stduy.social.domain.CreatePostEvent;
import com.stduy.social.domain.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {
    private final CreatePostPort createPostPort;
    private final RecordEventPort recordEventPort;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Long createPost(CreatePostRequest request) {
        Post post = request.toEntity();
        Long postId = createPostPort.createPost(post);

        CreatePostEvent createPostEvent = new CreatePostEvent(post);
        recordEventPort.record(createPostEvent.toEntity());

        eventPublisher.publishEvent(createPostEvent);
        return postId;
    }
}
