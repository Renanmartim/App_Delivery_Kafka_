package com.Client.Client.Service.impl;

import com.Client.Client.Model.StatusLogClientModel;
import com.Client.Client.Service.LogOrderService;
import com.Client.Client.Repository.StatusClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogOrderServiceImpl implements LogOrderService {

    private final StatusClientRepository statusClientRepository;

    private LogOrderServiceImpl (StatusClientRepository statusClientRepository){
        this.statusClientRepository = statusClientRepository;
    }
    @Override
    public ResponseEntity<String> takeStatus(String id) {

        Optional<StatusLogClientModel> client = statusClientRepository.findById(id);

        if(client.isPresent()){
            var myClientClass = client.get();

            String status = myClientClass.getStatus() + " | " + myClientClass.getTime();

            return ResponseEntity.ok().body(status);
        }

        return ResponseEntity.badRequest().body("ID not exists!");
    }
}
