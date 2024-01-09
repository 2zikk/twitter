package com.stduy.social.application.event;

import com.stduy.social.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomPostDeleteEventListener implements PostDeleteEventListener {
    private final ExecutorService executorService;

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        log.info("emit post delete event");
        if (event.getEntity() instanceof Post) {
            executorService.submit(() -> log.info("run thread"));
        }
    }

    /*
     * 리스너가 커밋 이후에 발생하는 이벤트를 처리할 필요가 있는지 여부
     * true: 리스너가 데이터베이스 트랜잭션이 커밋된 후에 호출되어야 함. 다른 트랜잭션에 의존하는 작업을 처리하는 데 사용.
     * false: 리스너가 트랜잭션이 아직 커밋되지 않은 상태에서도 호출될 수 있음을 의미. 리스너는 데이터가 아직 데이터베이스에 영구적으로 저장되지 않았을 수도 있는 상태에서 실행될 수 있음.
     * */
    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
