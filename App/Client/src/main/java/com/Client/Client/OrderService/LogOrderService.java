package com.Client.Client.OrderService;

import org.springframework.http.ResponseEntity;

public interface LogOrderService {

    public ResponseEntity<String> takeStatus (String id);
}
