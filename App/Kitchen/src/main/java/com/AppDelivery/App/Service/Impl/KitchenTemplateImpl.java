package com.AppDelivery.App.Service.Impl;

import com.AppDelivery.App.Service.KitchenTemplate;
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
public class KitchenTemplateImpl implements KitchenTemplate {
    @Value("${topic.client}")
    private String clientTopic;

    @Value("${topic.delivery}")
    private String deliveryTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KitchenTemplateImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMensageDelivery(String message) {

        kafkaTemplate.send(deliveryTopic, message);
    }

    public void sendMensageClient(String message) {

        kafkaTemplate.send(clientTopic, message);
    }


}
