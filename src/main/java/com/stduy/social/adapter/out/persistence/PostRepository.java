package com.stduy.social.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stduy.social.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
