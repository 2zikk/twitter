package com.stduy.social.domain;

import org.springframework.context.ApplicationEvent;

public class PostCreatedEvent extends ApplicationEvent {
    private final Long postId;

    public PostCreatedEvent(Object source, Long postId) {
        super(source);
        this.postId = postId;
    }

    public Long getPostId() {
        return this.postId;
    }
}
