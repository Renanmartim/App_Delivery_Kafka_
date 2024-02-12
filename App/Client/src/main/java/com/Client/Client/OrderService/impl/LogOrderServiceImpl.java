package com.Client.Client.OrderService.impl;

import com.Client.Client.OrderModel.StatusLogClientEntity;
import com.Client.Client.OrderService.LogOrderService;
import com.Client.Client.Repository.StatusClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogOrderServiceImpl implements LogOrderService {

    private StatusClientRepository statusClientRepository;

    private LogOrderServiceImpl (StatusClientRepository statusClientRepository){
        this.statusClientRepository = statusClientRepository;
    }
    @Override
    public ResponseEntity<String> takeStatus(String id) {

        Optional<StatusLogClientEntity> client = statusClientRepository.findById(id);

        if(client.isPresent()){
            var myClientClass = client.get();

            String status = myClientClass.getStatus() + " | " + myClientClass.getTime();

            return ResponseEntity.ok().body(status);
        }

        return ResponseEntity.badRequest().body("ID not exists!");
    }
}
