package com.Client.Client.Controller;

import com.Client.Client.OrderDocument.Entity;
import com.Client.Client.OrderService.ClientListener;
import com.Client.Client.OrderService.ClientTemplate;
import com.Client.Client.OrderService.LogOrderService;
import com.Client.Client.OrderService.impl.ClientListenerImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.jsr310.LocalDateCodec;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ClientController {

    private ClientTemplate client;

    private LogOrderService logOrderService;

    private ClientListener clientSubscriberimpl;

    private ClientController(ClientTemplate client, ClientListener clientSubscriberimpl, LogOrderService logOrderService){

        this.clientSubscriberimpl = clientSubscriberimpl;
        this.client = client;
        this.logOrderService = logOrderService;

    }

    @PostMapping
    public String send(@RequestBody Entity entity) {
        String orderForTopic = entity.id + " | " + entity.name + " ; " + entity.description + " : Send To Kitchen ";
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String clientmensage = entity.id + "|Send to Kitchen" + "(" + formattedDateTime + ")";
        client.sendMensageKitchen(orderForTopic);
        client.sendMensageClient(clientmensage);
        return orderForTopic;
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> status(@PathVariable String id) {
        return logOrderService.takeStatus(id);
    }
}
