package com.Client.Client.OrderService.impl;

import com.Client.Client.OrderDocument.StatusLogClientEntity;
import com.Client.Client.OrderService.ClientListener;
import com.Client.Client.Repository.StatusClientRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientListenerImpl implements ClientListener {

    private final ConcurrentHashMap<UUID, String> orderStatusMap;

    private StatusClientRepository statusClientRepository;

    private StatusLogClientEntity statusLogClientEntity;

    private boolean kafkaInstant = true;

    public ClientListenerImpl(ConcurrentHashMap<UUID, String> orderStatusMap, StatusClientRepository statusClientRepository) {
        this.orderStatusMap = orderStatusMap;
        this.statusClientRepository = statusClientRepository;
    }

    public void enableKafkaInstant() {
        kafkaInstant = true;
    }

    @KafkaListener(topics = "client_topic", groupId = "my_status_group")
    public void consumeOrderEvent(String recordValue) {
        if (kafkaInstant) {

            String[] parts = recordValue.split("\\|");

            var eventNew = new StatusLogClientEntity(parts[0], parts[1]);

            statusClientRepository.save(eventNew);

            System.out.println(eventNew);
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
