package com.stduy.social.domain.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stduy.social.domain.Post;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreatePostEvent {
    private Long postId;
    private String title;
    private String content;

    public CreatePostEvent(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public CreatePostEvent() {
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

    @Override
    public String toString() {
        return "CreatePostEvent{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
