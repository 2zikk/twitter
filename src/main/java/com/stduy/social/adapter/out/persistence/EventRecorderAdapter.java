package com.stduy.social.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.stduy.social.application.port.out.RecordEventPort;
import com.stduy.social.domain.event.EventRecorder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EventRecorderAdapter implements RecordEventPort {
    private final EventRecorderRepository eventRecorderRepository;

    @Override
    public void record(EventRecorder eventRecorder) {
        eventRecorderRepository.save(eventRecorder);
    }
}
