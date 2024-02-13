package com.Client.Client.Service;

import org.springframework.http.ResponseEntity;

public interface LogOrderService {

    public ResponseEntity<String> takeStatus (String id);
}
