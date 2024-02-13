package com.Client.Client.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client_user")
public class ClientModel {

    @Id
    private String id;

    private String name;

    private Long cep;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCep() {
        return cep;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public ClientModel(String id, String name, Long cep) {
        this.id = id;
        this.name = name;
        this.cep = cep;
    }
}
