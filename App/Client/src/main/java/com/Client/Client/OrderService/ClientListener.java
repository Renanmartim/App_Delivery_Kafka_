package com.Client.Client.OrderService;

public interface ClientListener {


    public void enableKafkaInstant();

    public void consumeOrderEvent(String recordValue);

    public String getOrderStatus(String orderId);
}