package com.Client.Client.Controller;

import com.Client.Client.OrderModel.Client;
import com.Client.Client.OrderModel.Entity;
import com.Client.Client.OrderService.ClientListener;
import com.Client.Client.OrderService.ClientTemplate;
import com.Client.Client.OrderService.ClientUser;
import com.Client.Client.OrderService.LogOrderService;
import lombok.extern.slf4j.Slf4j;
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

    private ClientUser clientUser;

    private ClientController(ClientTemplate client, ClientListener clientSubscriberimpl, LogOrderService logOrderService, ClientUser clientUser){

        this.clientSubscriberimpl = clientSubscriberimpl;
        this.client = client;
        this.clientUser = clientUser;
        this.logOrderService = logOrderService;

    }

    @PostMapping
    public String send(@RequestBody Entity entity) {

        var verifyId = clientUser.verifyClient(entity.id);

        if (verifyId) {

            String orderForTopic = entity.id + " | " + entity.name + " ; " + entity.description + " : Send To Kitchen ";
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            String clientmensage = entity.id + "|Send to Kitchen" + "(" + formattedDateTime + ")";
            client.sendMensageKitchen(orderForTopic);
            client.sendMensageClient(clientmensage);
            return orderForTopic;
        }

        return "This Client Not Exists!";
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> status(@PathVariable String id) {
        return logOrderService.takeStatus(id);
    }

    @PostMapping("user/create")
    public ResponseEntity<Client> create(@RequestBody Client client){
        return clientUser.register(client);
    }

    @PostMapping("user/modify/{id}")
    public ResponseEntity<String> modify(@PathVariable String id, @RequestBody Client client){
        return clientUser.modify(id,client);
    }
}
