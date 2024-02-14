package com.Client.Client.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAdress {

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("number")
    private Integer number;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserAdress(String cep, Integer number) {
        this.cep = cep;
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserAdress{" +
                "cep='" + cep + '\'' +
                ", number=" + number +
                '}';
    }
}
