package com.stduy.social.app.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stduy.social.app.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
