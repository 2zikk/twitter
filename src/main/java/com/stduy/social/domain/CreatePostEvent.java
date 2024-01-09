package com.stduy.social.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreatePostEvent {
    private Long postId;
    private String title;
    private String content;

    public CreatePostEvent(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public EventRecorder toEntity() {
        final EventRecorder eventRecorder;
        try {
            eventRecorder = new EventRecorder(new ObjectMapper().writeValueAsString(this));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return eventRecorder;
    }
}
