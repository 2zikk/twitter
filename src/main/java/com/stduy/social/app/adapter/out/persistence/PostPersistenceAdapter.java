package com.stduy.social.app.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.stduy.social.app.application.port.out.CreatePostPort;
import com.stduy.social.app.domain.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PostPersistenceAdapter implements CreatePostPort {
    private final PostRepository postRepository;

    @Override
    public Long createPost(Post post) {
        Post saved = postRepository.save(post);
        return saved.getId();
    }
}
