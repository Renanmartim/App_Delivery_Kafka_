package com.Client.Client.Service.impl;

import com.Client.Client.Model.ClientModel;
import com.Client.Client.Service.ClientUser;
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
    public ResponseEntity<ClientModel> register(ClientModel client) {
        var startClient = clientRepository.save(client);
        return ResponseEntity.ok().body(startClient);
    }

    @Override
    public ResponseEntity<String> modify(String id, ClientModel client) {

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
