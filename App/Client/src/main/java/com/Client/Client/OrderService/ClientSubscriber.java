package com.Client.Client.OrderService;

import com.Client.Client.OrderService.impl.ClientSubsCriberImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientSubscriber implements ClientSubsCriberImpl {

    private final ConcurrentHashMap<UUID, String> orderStatusMap;

    private boolean kafkaInstant = true;

    public ClientSubscriber(ConcurrentHashMap<UUID, String> orderStatusMap) {
        this.orderStatusMap = orderStatusMap;
    }

    public void enableKafkaInstant() {
        kafkaInstant = true;
    }

    @KafkaListener(topics = "kitechen_delivery_topic", groupId = "my_status_group")
    public void consumeOrderEvent(String recordValue) {
        if (kafkaInstant) {

            System.out.println(orderStatusMap);
            // Split the input message using different separators
            String[] parts = recordValue.split("\\|");

            if (parts.length >= 2) {
                // Extract order ID and status
                String orderId = parts[0].trim();
                String[] statusParts = parts[1].split(":");

                if (statusParts.length >= 1) {

                    String status = statusParts[1].trim();

                    // Update order status map
                    orderStatusMap.put(UUID.fromString(orderId), status);

                    System.out.println(orderStatusMap);

                    // Print the received order event
                    System.out.println("Received order event: OrderId=" + orderId + ", Status=" + status);
                } else {
                    System.out.println("Invalid message format: " + recordValue);
                }
            } else {
                System.out.println("Invalid message format: " + recordValue);
            }
        }
    }

    public String getOrderStatus(String orderId) {
        if (!orderStatusMap.isEmpty()) {
            UUID uuidOrderId = UUID.fromString(orderId);
            return orderStatusMap.getOrDefault(uuidOrderId, "None");
        }
        return "None";
    }

}
