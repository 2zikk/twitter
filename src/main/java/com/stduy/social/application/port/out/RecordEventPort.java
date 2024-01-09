package com.stduy.social.application.port.out;

import com.stduy.social.domain.event.EventRecorder;

public interface RecordEventPort {
    void record(EventRecorder eventRecorder);
}
