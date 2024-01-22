package com.Delivery.Delivery.DeliveryService;

import com.Delivery.Delivery.DeliveryService.Impl.DeliveryListenerImpl;
import com.Delivery.Delivery.DeliveryService.Impl.DeliveryTemplateImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DeliveryListener implements DeliveryListenerImpl {

    public DeliveryTemplateImpl deliveryTemplate;

    public DeliveryListener (DeliveryTemplateImpl deliveryTemplate){

        this.deliveryTemplate = deliveryTemplate;

    }

    @KafkaListener(topics = "delivery_topic", groupId = "my-group-id-1")
    public void listen(String recordValue) {

        String[] parts = recordValue.split("\\|");

        System.out.println(parts.length);

        if (parts.length >= 1) {
            // Extract order ID and status
            System.out.println(Arrays.toString(parts));
            String orderId = parts[0].trim();
            String[] statusParts = parts[1].split(";");

            if (statusParts.length >= 1) {

                String order = orderId + " | Delivering...";

                // Print the received order event
                System.out.println(order);

                deliveryTemplate.sendMensage(order);

            } else {
                System.out.println("Invalid message format1: " + recordValue);
            }
        } else {
            System.out.println("Invalid message format2: " + recordValue);
        }
    }

}
