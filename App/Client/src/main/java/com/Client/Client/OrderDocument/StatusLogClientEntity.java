package com.Client.Client.OrderDocument;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class StatusLogClientEntity {

    @Id
    public String id;

    public String status;

    public LocalDateTime time;

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public StatusLogClientEntity(String id, String status, LocalDateTime time) {
        this.id = id;
        this.status = status;
        this.time = time;
    }

    @Override
    public String toString() {
        return "StatusLogClientEntity{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                '}';
    }
}
