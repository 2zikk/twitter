package com.stduy.social.application.command;

import com.stduy.social.application.dto.CreatePostRequest;
import com.stduy.social.application.port.out.CreatePostPort;
import com.stduy.social.application.port.out.RecordEventPort;
import com.stduy.social.domain.Post;
import com.stduy.social.domain.event.CreatePostEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {
    @Value("${spring.kafka.topic.post}")
    private String topic;

    private final CreatePostPort createPostPort;
    private final RecordEventPort recordEventPort;
    private final KafkaTemplate<String, CreatePostEvent> kafkaProducer;

    @Transactional
    public Long createPost(CreatePostRequest request) {
        Post post = request.toEntity();
        Long postId = createPostPort.createPost(post);

        CreatePostEvent createPostEvent = new CreatePostEvent(post);
        recordEventPort.record(createPostEvent.toEntity());

        kafkaProducer.send(topic, createPostEvent);

        return postId;
    }
}
