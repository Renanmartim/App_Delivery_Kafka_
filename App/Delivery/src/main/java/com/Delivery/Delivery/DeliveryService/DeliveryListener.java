package com.Delivery.Delivery.DeliveryService;

import com.Delivery.Delivery.DeliveryService.Impl.DeliveryListenerImpl;
import com.Delivery.Delivery.DeliveryService.Impl.DeliveryTemplateImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DeliveryListener implements DeliveryListenerImpl {

    public DeliveryTemplateImpl deliveryTemplate;

    public DeliveryListener(DeliveryTemplateImpl deliveryTemplate) {

        this.deliveryTemplate = deliveryTemplate;

    }

    @KafkaListener(topics = "delivery_topic", groupId = "my-group-id-1")
    public void listen(String recordValue) throws InterruptedException {

        String[] parts = recordValue.split("\\|");

        Thread.sleep(5000);

        String order = extractOrderId(parts[0]) + "|Delivering...";

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
