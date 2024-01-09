package com.stduy.social.application.aop;

import com.stduy.social.adapter.in.consumer.PostEventListener;
import com.stduy.social.domain.event.PostCreatedEvent;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostCreationAspect {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private static final Logger logger = LoggerFactory.getLogger(PostEventListener.class);


    @AfterReturning(value = "execution(* com.stduy.social.application.port.out.CreatePostPort.createPost(..))",
            returning = "postId")
    public void afterPostCreated(Long postId) {
        logger.info("publish post event: {}", postId);

        PostCreatedEvent event = new PostCreatedEvent(this, postId);
        eventPublisher.publishEvent(event);
    }
}