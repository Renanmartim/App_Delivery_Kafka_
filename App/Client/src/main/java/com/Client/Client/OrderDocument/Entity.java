package com.Client.Client.OrderDocument;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Getter
@Setter
@Builder
public class Entity {

    @Id
    public String id;

    public String name;

    public String description;
}
