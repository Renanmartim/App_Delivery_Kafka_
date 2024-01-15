package com.Client.Client.OrderService;

import com.Client.Client.OrderDocument.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
@Setter
@Data
public class Client {


    @Value("${topic.kitchen_delivery}")
    public String kitechenTopic;

    public static boolean kafkaInstant = false;

    private KafkaTemplate<String, Object> kafkaTemplate;

    private ConcurrentHashMap<Integer, String> orderStatusMap = new ConcurrentHashMap<>();

    public Client(KafkaTemplate<String, Object> kafkaTemplate, ConcurrentHashMap<Integer, String> orderStatusMap) {

        this.orderStatusMap = orderStatusMap;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void changeStatusKafka(){
        kafkaInstant = true;
    }


    public void sendMensage(String mensage) {
        kafkaTemplate.send(kitechenTopic,mensage);
    }


    @KafkaListener(topics = "kitechen_delivery_topic", groupId = "my_status_group")
    public void consumeOrderEvent(ConsumerRecord<String, String> record) {
        if (kafkaInstant) {
            String id = record.value();
            String[] partsid = id.split("\\|");
            String status = record.value();
            String[] partsstatus = status.split(":");
            orderStatusMap.put(Integer.valueOf(partsid[0]), partsstatus[0]);
            System.out.println("Received order event: OrderId=" + Integer.valueOf(partsid[0]) + ", Status=" + partsstatus[0]);
        }
    }

    public String getOrderStatus(int orderId) {
        return orderStatusMap.getOrDefault(orderId, "Order not found");
    }
}
