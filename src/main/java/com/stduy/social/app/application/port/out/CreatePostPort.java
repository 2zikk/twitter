package com.stduy.social.app.application.port.out;

import com.stduy.social.app.domain.Post;

public interface CreatePostPort {
    Long createPost(Post post);
}
