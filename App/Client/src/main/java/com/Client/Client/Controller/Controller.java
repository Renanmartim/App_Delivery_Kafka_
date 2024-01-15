package com.Client.Client.Controller;

import com.Client.Client.OrderDocument.Entity;
import com.Client.Client.OrderService.Client;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class Controller {

    @Autowired
    private Client client;

    @PostMapping
    public String send(@RequestBody Entity entity) {
        String orderForTopic = entity.name + " ; " + entity.description + " : Send To Kitchen ";
        client.sendMensage(orderForTopic);
        return "Order Dispatched";
    }

    @GetMapping("/status/{id}")
    public String status(@RequestParam Integer id) {
        client.changeStatusKafka();
        return client.getOrderStatus(id);
    }
}
