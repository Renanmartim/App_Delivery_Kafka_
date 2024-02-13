package com.Client.Client.Service.impl;

import com.Client.Client.Model.StatusLogClientModel;
import com.Client.Client.Service.ClientListener;
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

        var eventNew = new StatusLogClientModel(parts[0], status, date);

        statusClientRepository.save(eventNew);

    }


}
