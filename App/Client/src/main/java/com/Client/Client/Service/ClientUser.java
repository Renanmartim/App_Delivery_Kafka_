package com.Client.Client.Service;


import com.Client.Client.Model.ClientModel;
import com.Client.Client.Model.EntityModel;
import org.springframework.http.ResponseEntity;


public interface ClientUser {

    public ResponseEntity<ClientModel> register(ClientModel client);

    public ResponseEntity<String> modify(String id, ClientModel client);

    public Boolean verifyClient(String id);

    public String sendMensage(EntityModel entity);
}
