package com.stduy.social.adapter.out.persistence.event;

import com.stduy.social.adapter.out.persistence.event.CustomPostDeleteEventListener;
import com.stduy.social.adapter.out.persistence.event.CustomPostInsertEventListener;
import com.stduy.social.adapter.out.persistence.event.CustomPostUpdateEventListener;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HibernateListener {
    private final EntityManagerFactory entityManagerFactory;
    private final CustomPostInsertEventListener customPostInsterEventListener;
    private final CustomPostUpdateEventListener customPostUpdateEventListener;
    private final CustomPostDeleteEventListener customPostDeleteEventListener;

    @PostConstruct
    private void init() {
        log.info("Initializing HibernateListener");
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(customPostInsterEventListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(customPostUpdateEventListener);
        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(customPostDeleteEventListener);
    }
}

