package com.Client.Client.Controller;

import com.Client.Client.OrderDocument.Entity;
import com.Client.Client.OrderService.ClientTemplate;
import com.Client.Client.OrderService.impl.ClientListenerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ClientController {

    public ClientTemplate client;

    public ClientListenerImpl clientSubscriberimpl;

    public ClientController(ClientTemplate client, ClientListenerImpl clientSubscriberimpl){

        this.clientSubscriberimpl = clientSubscriberimpl;
        this.client = client;

    }

    @PostMapping
    public String send(@RequestBody Entity entity) {
        String orderForTopic = entity.id + " | " + entity.name + " ; " + entity.description + " : Send To Kitchen ";
        String clientmensage = entity.id + "| Send to Kitchen";
        client.sendMensageKitchen(orderForTopic);
        client.sendMensageClient(clientmensage);
        return orderForTopic;
    }

    @GetMapping("/status/{id}")
    public String status(@PathVariable String id) {
        clientSubscriberimpl.enableKafkaInstant();
        return clientSubscriberimpl.getOrderStatus(id);
    }
}
