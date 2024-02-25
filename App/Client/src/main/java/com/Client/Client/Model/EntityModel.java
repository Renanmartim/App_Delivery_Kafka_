package com.Client.Client.Model;


import com.Client.Client.Dto.CardPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityModel {

    private String id;
    private String name;
    private String description;

    @JsonProperty("cardPayment")
    private CardPayment cardPayment;

    public EntityModel() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CardPayment getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(CardPayment cardPayment) {
        this.cardPayment = cardPayment;
    }
}


