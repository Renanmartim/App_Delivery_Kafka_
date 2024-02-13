package com.Client.Client.Model;


import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityModel {

    public String id;
    public String name;
    public String description;

    public EntityModel() {
        this.id = UUID.randomUUID().toString();
    }
}


