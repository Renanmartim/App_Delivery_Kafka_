package com.Client.Client.Model;

import com.Client.Client.Dto.UserAdress;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client_user")
public class ClientModel {

    @Id
    private String id;

    private String name;

    private Long cpf;

    private UserAdress userAdress;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public UserAdress getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(UserAdress userAdress) {
        this.userAdress = userAdress;
    }

    public ClientModel(String name, Long cpf, UserAdress userAdress) {
        this.name = name;
        this.cpf = cpf;
        this.userAdress = userAdress;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cpf=" + cpf +
                ", userAdress=" + userAdress +
                '}';
    }
}



