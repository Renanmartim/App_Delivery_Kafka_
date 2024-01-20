package com.Client.Client.OrderService.impl;

public interface ClientSubsCriberImpl {


    public void enableKafkaInstant();

    public void consumeOrderEvent(String recordValue);

    public String getOrderStatus(String orderId);
}
