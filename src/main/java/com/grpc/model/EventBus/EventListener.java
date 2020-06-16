package com.grpc.model.EventBus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        log.info("Subscribe message:{}", event);
    }
}
