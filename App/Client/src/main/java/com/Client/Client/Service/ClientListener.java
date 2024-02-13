package com.Client.Client.Service;

public interface ClientListener {


    public void enableKafkaInstant();

    public void consumeOrderEvent(String recordValue);

}