package com.Delivery.Delivery.DeliveryService.Impl;

public interface DeliveryListenerImpl {

    public void listen(String recordValue) throws InterruptedException;

    public String extractOrderId(String input);

}
