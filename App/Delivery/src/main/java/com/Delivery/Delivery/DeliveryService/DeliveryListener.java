package com.Delivery.Delivery.DeliveryService;

public interface DeliveryListener {

    public void listen(String recordValue) throws InterruptedException;

    public String extractOrderId(String input);

}
