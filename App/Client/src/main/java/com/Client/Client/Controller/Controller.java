package com.Client.Client.Controller;

import com.Client.Client.OrderDocument.Entity;
import com.Client.Client.OrderService.Client;
import com.Client.Client.OrderService.ClientSubscriber;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
@Slf4j
public class Controller {

    public Client client;

    public ClientSubscriber clientSubscriber;

    public Controller (Client client, ClientSubscriber clientSubscriber){

        this.clientSubscriber = clientSubscriber;
        this.client = client;

    }

    @PostMapping
    public String send(@RequestBody Entity entity) {
        String orderForTopic = entity.id + " | " + entity.name + " ; " + entity.description + " : Send To Kitchen ";
        client.sendMensage(orderForTopic);
        return orderForTopic;
    }

    @GetMapping("/status/{id}")
    public String status(@PathVariable String id) {
        clientSubscriber.enableKafkaInstant();
        return clientSubscriber.getOrderStatus(id);
    }
}
