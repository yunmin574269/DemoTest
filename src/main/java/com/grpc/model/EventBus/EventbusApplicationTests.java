package com.grpc.model.EventBus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventbusApplicationTests {

    @Autowired
    private EventBusHandler eventBusHandler;

    @Test
    public void contextLoads() {
        eventBusHandler.eventPost();
    }
}
