package com.stduy.social.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stduy.social.domain.EventRecorder;

public interface EventRecorderRepository extends JpaRepository<EventRecorder, Long> {
}
