package com.AppDelivery.App.General;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Value("${topic.kitchen_delivery}")
    public String kitechenTopic;

    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "kitechen_delivery_topic", groupId = "my-group-id")
    public void listen(ConsumerRecord<String, String> record) throws InterruptedException {

        String message = record.value();

        String[] parts = message.split("\\|");

        Thread.sleep(3000);

        sendMensage(parts[0] + " Order Ready ");
    }

    public void sendMensage(String mensage) {
        kafkaTemplate.send(kitechenTopic,mensage);
    }

}
