package com.stduy.social.application.command;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.stduy.social.domain.CreatePostEvent;

@Component
public class PostEventUseCase {

    @TransactionalEventListener
    public void handleCreatePostEvent(CreatePostEvent event) {
        // 카프카 전송
    }
}
