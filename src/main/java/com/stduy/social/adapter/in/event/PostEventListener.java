package com.stduy.social.adapter.in.event;

import com.stduy.social.domain.event.PostCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PostEventListener {

    private static final Logger logger = LoggerFactory.getLogger(PostEventListener.class);

    @EventListener
    public void onPostCreated(PostCreatedEvent event) {
        logger.info("receive post event: {}", event.getPostId());
    }
}
