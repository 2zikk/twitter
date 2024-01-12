package com.stduy.social.adapter.in.consumer;


import com.stduy.social.domain.event.CreatePostEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPostEventListener {
    @KafkaListener(
            topics = "${spring.kafka.topic.post}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void onPostSave(CreatePostEvent event) {
        System.out.println("Received CreatePostEvent: " + event.toString());
    }
}
