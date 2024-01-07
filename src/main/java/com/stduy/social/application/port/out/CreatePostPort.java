package com.stduy.social.application.port.out;

import com.stduy.social.domain.Post;

public interface CreatePostPort {
    Long createPost(Post post);
}
