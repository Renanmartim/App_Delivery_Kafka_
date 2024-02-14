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
}


