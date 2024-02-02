package com.Client.Client.OrderDocument;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StatusLogClientEntity {

    @Id
    public String id;

    public String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StatusLogClientEntity(String id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusLogClientEntity{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
