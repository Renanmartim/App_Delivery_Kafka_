package com.Client.Client.OrderService.impl;

import com.Client.Client.OrderModel.StatusLogClientEntity;
import com.Client.Client.OrderService.ClientListener;
import com.Client.Client.Repository.StatusClientRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ClientListenerImpl implements ClientListener {

    private StatusClientRepository statusClientRepository;

    private boolean kafkaInstant = true;

    public ClientListenerImpl(StatusClientRepository statusClientRepository) {
        this.statusClientRepository = statusClientRepository;
    }

    public void enableKafkaInstant() {
        kafkaInstant = true;
    }

    @KafkaListener(topics = "client_topic", groupId = "status_client")
    public void consumeOrderEvent(String recordValue) {

        String[] parts = recordValue.split("\\|");

        var dateStatus = parts[1];

        int openParenIndex = dateStatus.indexOf('(');
        int closeParenIndex = dateStatus.indexOf(')');

        String status = dateStatus.substring(0, openParenIndex).trim();

        String dateText = dateStatus.substring(openParenIndex + 1, closeParenIndex).trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(dateText, formatter);

        var eventNew = new StatusLogClientEntity(parts[0], status, date);

        statusClientRepository.save(eventNew);

    }


}
