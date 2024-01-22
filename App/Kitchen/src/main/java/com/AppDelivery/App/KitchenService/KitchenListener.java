package com.AppDelivery.App.KitchenService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class KitchenListener {

    public KitchenTemplate kitchenTemplate;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KitchenListener(KafkaTemplate<String, Object> kafkaTemplate, KitchenTemplate kitchenTemplate) {

        this.kitchenTemplate = kitchenTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "kitechen_delivery_topic", groupId = "my-group-id")
    public void listen(String recordValue) throws InterruptedException {

        String[] parts = recordValue.split("\\|");

        if (parts.length >= 2) {
            // Extract order ID and status
            String orderId = parts[0].trim();
            String[] statusParts = parts[1].split(";");

            if (statusParts.length >= 1) {

                String name = statusParts[0].trim();

                String description = statusParts[1].trim();

                int indexOfColon = description.indexOf(" : ");

                String result = description.substring(0, indexOfColon);

                String order = "Received order event : OrderId= " + orderId + " | Order= " + name + " ; Description= " + result;

                String client = orderId + " | Send to Delivery";

                // Print the received order event
                System.out.println(order);

                Thread.sleep(100);

                kitchenTemplate.sendMensageDelivery(order);

                kitchenTemplate.sendMensageClient(client);

            } else {
                System.out.println("Invalid message format: " + recordValue);
            }
        } else {
            System.out.println("Invalid message format: " + recordValue);
        }
    }


}
