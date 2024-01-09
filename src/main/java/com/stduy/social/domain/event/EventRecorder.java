package com.stduy.social.domain.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EventRecorder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String payload;

    private boolean published;

    public EventRecorder(String payload) {
        this.payload = payload;
    }
}
