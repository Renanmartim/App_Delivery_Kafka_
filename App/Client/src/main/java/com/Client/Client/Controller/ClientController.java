package com.Client.Client.Controller;

import com.Client.Client.Exceptions.InvalidCepException;
import com.Client.Client.Model.ClientModel;
import com.Client.Client.Model.EntityModel;
import com.Client.Client.Service.ClientListener;
import com.Client.Client.Service.ClientTemplate;
import com.Client.Client.Service.ClientUser;
import com.Client.Client.Service.LogOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ClientController {


    private final LogOrderService logOrderService;

    private final ClientUser clientUser;

    private ClientController(LogOrderService logOrderService, ClientUser clientUser){

        this.logOrderService = logOrderService;
        this.clientUser = clientUser;

    }

    @PostMapping
    public String send(@RequestBody EntityModel entity) {
        return clientUser.sendMensage(entity);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> status(@PathVariable String id) {
        return logOrderService.takeStatus(id);
    }

    @PostMapping("user/create")
    public ResponseEntity<ClientModel> create(@RequestBody ClientModel client){

        var createUser = clientUser.register(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createUser.getBody())
                .toUri();
        return ResponseEntity.created(location).body(createUser.getBody());
    }

    @PostMapping("user/modify/{id}")
    public ResponseEntity<String> modify(@PathVariable String id, @RequestBody ClientModel client){
        return clientUser.modify(id,client);
    }

}
