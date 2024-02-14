package com.Client.Client.Service.impl;

import com.Client.Client.Dto.ZipCodeResponse;
import com.Client.Client.Model.ClientModel;
import com.Client.Client.Service.ClientUser;
import com.Client.Client.Repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientUserImpl implements ClientUser {

    private ClientRepository clientRepository;

    private ClientUserImpl (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<ClientModel> register(ClientModel client) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://brasilaberto.com/api/v1/zipcode/" + client.getUserAdress().getCep();

        try {

            String jsonResponse = restTemplate.getForObject(url, String.class);

                var startClient = clientRepository.save(client);

                return ResponseEntity.ok().body(startClient);

        } catch (HttpClientErrorException.NotFound ex) {

            String responseBody = ex.getResponseBodyAsString();

            System.out.println("The Cep Is Incorrect! Try Again!");

        } catch (Exception ex) {

            ex.printStackTrace();

        }
        return null;

    }

    @Override
    public ResponseEntity<String> modify(String id, ClientModel client) {

        var startClient = clientRepository.findById(id);

        if(startClient.isPresent()){
            var clientObject = startClient.get();

            clientObject.setUserAdress(client.getUserAdress());
            clientObject.setCpf(client.getCpf());
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
