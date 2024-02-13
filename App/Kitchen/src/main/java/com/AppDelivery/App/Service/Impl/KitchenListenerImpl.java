package com.AppDelivery.App.Service.Impl;

import com.AppDelivery.App.Service.KitchenListener;
import com.AppDelivery.App.Service.KitchenTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KitchenListenerImpl implements KitchenListener {

    private KitchenTemplate kitchenTemplate;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private KitchenListenerImpl(KafkaTemplate<String, Object> kafkaTemplate, KitchenTemplate kitchenTemplate) {

        this.kitchenTemplate = kitchenTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "kitechen_delivery_topic", groupId = "status_kitechen")
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

                String order = "Received order event : OrderId= " + orderId + "| Order= " + name + " ; Description= " + result;

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);

                String client = orderId + "|Send to Delivery" + "(" + formattedDateTime + ")";

                Thread.sleep(100000);

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
