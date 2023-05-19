package com.startupsdigidojo.virtualspaces.note.application.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void generate(String message) {
        String jsonMessage = messageToJson(message);
        kafkaTemplate.send("topic_0", jsonMessage);
    }

    private String messageToJson(String message) {
        return "{\"message\":\""+message+"\"}";
    }
}