package com.Client.Client.OrderService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
@Setter
@Data
public class Client {

    @Value("${topic.kitchen_delivery}")
    private String kitchenTopic;



    private final KafkaTemplate<String, Object> kafkaTemplate;


    public Client(KafkaTemplate<String, Object> kafkaTemplate, ConcurrentHashMap<UUID, String> orderStatusMap) {
        this.kafkaTemplate = kafkaTemplate;
    }



    public void sendMensage(String message) {
        kafkaTemplate.send(kitchenTopic, message);
    }


}
