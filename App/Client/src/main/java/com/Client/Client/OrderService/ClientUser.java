package com.Client.Client.OrderService;


import com.Client.Client.OrderModel.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface ClientUser {

    public ResponseEntity<Client> register(Client client);

    public ResponseEntity<String> modify(String id, Client client);

    public Boolean verifyClient(String id);
}
