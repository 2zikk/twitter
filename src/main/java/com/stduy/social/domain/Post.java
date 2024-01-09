package com.stduy.social.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String author;

    private String title;

    private String content;

    public static Post CreatePost(String author, String title, String content) {
        return new Post(null, author, title, content);
    }

    protected Post() {
    }

    public Post(Long postId, String author, String title, String content) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
