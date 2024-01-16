package com.Client.Client.OrderDocument;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Entity {

    public String id;
    public String name;
    public String description;

    public Entity() {
        this.id = UUID.randomUUID().toString();
    }
}


