package com.Client.Client.OrderService.impl;

import com.Client.Client.OrderModel.Client;
import com.Client.Client.OrderService.ClientUser;
import com.Client.Client.Repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientUserImpl implements ClientUser {

    private ClientRepository clientRepository;

    private ClientUserImpl (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<Client> register(Client client) {
        var startClient = clientRepository.save(client);
        return ResponseEntity.ok().body(startClient);
    }

    @Override
    public ResponseEntity<String> modify(String id, Client client) {

        var startClient = clientRepository.findById(id);

        if(startClient.isPresent()){
            var clientObject = startClient.get();

            clientObject.setCep(client.getCep());
            clientObject.setName(client.getName());

            clientRepository.save(clientObject);

            return ResponseEntity.ok().body("Change Made Successfully!");
        }

        return ResponseEntity.badRequest().body("Id Not Exists!");
    }

    @Override
    public Boolean verifyClient(String id) {

        var startClient = clientRepository.findById(id);

        return startClient.isPresent();

    }
}
