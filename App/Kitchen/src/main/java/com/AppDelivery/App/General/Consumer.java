package com.AppDelivery.App.General;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {



    @KafkaListener(topics = "kitechen_delivery_topic", groupId = "my-group-id")
    public void listen(String message) {


        System.out.println("Received message: " + message);

    }

}
