package com.Delivery.Delivery.Service.Impl;

import com.Delivery.Delivery.Service.DeliveryListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DeliveryListenerImpl implements DeliveryListener {

    public DeliveryTemplateImpl deliveryTemplate;

    public DeliveryListenerImpl(DeliveryTemplateImpl deliveryTemplate) {

        this.deliveryTemplate = deliveryTemplate;

    }

    @KafkaListener(topics = "delivery_topic", groupId = "status_delivery")
    public void listen(String recordValue) throws InterruptedException {

        String[] parts = recordValue.split("\\|");

        Thread.sleep(50000);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String order = extractOrderId(parts[0]) + "|Delivering" + "(" + formattedDateTime + ")";

        deliveryTemplate.sendMensage(order);
    }

    public String extractOrderId(String input) {

        Pattern pattern = Pattern.compile("OrderId=\\s*(.*)");

        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {

            return matcher.group(1);
        }
        return "UUID not found in the given example.";

    }

}
