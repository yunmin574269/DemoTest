package com.grpc.model.EventBus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zalando.stups.boot.eventbus.EventBusSupport;

@Component
@Slf4j
public class EventBusHandler {
    @Autowired
    private final EventBusSupport eventBusSupport;

    public EventBusHandler(final EventBusSupport eventBusSupport){
        this.eventBusSupport = eventBusSupport;
    }

    public void eventPost(){
        eventBusSupport.post(MessageEvent.builder().id(1).name("test").build());
        log.info("post event");
        eventBusSupport.postAsync(MessageEvent.builder().id(2).name("AsyncTest").build());
        log.info("post async event");
    }
}
