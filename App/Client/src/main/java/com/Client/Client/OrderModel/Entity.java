package com.Client.Client.OrderModel;


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


