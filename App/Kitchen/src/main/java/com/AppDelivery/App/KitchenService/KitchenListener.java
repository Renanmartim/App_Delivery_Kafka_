package com.AppDelivery.App.KitchenService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

public interface KitchenListener {

    public void listen(String recordValue) throws InterruptedException;


}
