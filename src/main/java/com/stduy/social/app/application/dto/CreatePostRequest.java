package com.stduy.social.app.application.dto;

import com.stduy.social.app.domain.Post;

import lombok.Getter;

@Getter
public class CreatePostRequest {
    private String author;
    private String title;
    private String content;

    public Post toEntity() {
        return Post.CreatePost(author, title, content);
    }
}
