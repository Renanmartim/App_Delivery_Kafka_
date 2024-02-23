package com.Payment.Payment.Service;

import com.Payment.Payment.Model.CardModel;
import com.Payment.Payment.Request.CardRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface PaymentService {

    public Mono<CardModel> save(CardModel cardModel);

    public Mono<String> payment(CardRequest cardRequest);
}
