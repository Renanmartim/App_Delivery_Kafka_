package com.Client.Client.OrderService.impl;

import com.Client.Client.OrderService.ClientTemplate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Data
public class ClientTemplateImpl implements ClientTemplate {

    @Value("${topic.client}")
    private String clientTopic;

    @Value("${topic.kitchen_delivery}")
    private String kitchenTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ClientTemplateImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void sendMensageKitchen(String message) {

        kafkaTemplate.send(kitchenTopic, message);

    }

    @Override
    public void sendMensageClient(String message) {

        kafkaTemplate.send(clientTopic, message);

    }
}
