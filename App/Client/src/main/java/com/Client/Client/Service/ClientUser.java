package com.Client.Client.Service;


import com.Client.Client.Model.ClientModel;
import com.Client.Client.Model.EntityModel;
import com.Client.Client.Response.ResponseCreate;
import org.springframework.http.ResponseEntity;


public interface ClientUser {

    public ResponseEntity<ResponseCreate> register(ClientModel client);

    public ResponseEntity<String> modify(String id, ClientModel client);

    public Boolean verifyClient(String id);

    public String sendMensage(EntityModel entity);
}
