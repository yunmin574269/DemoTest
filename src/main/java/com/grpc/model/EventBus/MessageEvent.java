package com.grpc.model.EventBus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageEvent {

    private Integer id;
    private String name;

}
