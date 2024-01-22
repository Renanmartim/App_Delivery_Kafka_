package com.Delivery.Delivery.DeliveryService;

import com.Delivery.Delivery.DeliveryService.Impl.DeliveryTemplateImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeliveryTemplate implements DeliveryTemplateImpl {

    @Value("${topic.client}")
    private String clientTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public DeliveryTemplate(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMensage(String message) {
        kafkaTemplate.send(clientTopic, message);
    }
}
