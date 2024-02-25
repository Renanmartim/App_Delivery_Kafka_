package com.Payment.Payment.Service.Impl;

import com.Payment.Payment.Model.CardModel;
import com.Payment.Payment.Repository.CardRepository;
import com.Payment.Payment.Request.CardRequest;
import com.Payment.Payment.Service.PaymentService;
import org.slf4j.helpers.NOP_FallbackServiceProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final CardRepository cardRepository;

    private PaymentServiceImpl(CardRepository cardRepository){
        this.cardRepository=cardRepository;
    }
    @Override
    public Mono<CardModel> save(CardModel cardModel) {
        return cardRepository.save(cardModel);
    }

    @Override
    public Mono<Boolean> payment(CardRequest cardRequest) {
        return cardRepository.findByNumber(cardRequest.getNumber())
                .flatMap(cardResult -> {
                    if (cardResult != null &&
                            Objects.equals(cardRequest.getName(), cardResult.getName()) &&
                            Objects.equals(cardRequest.getNumber(), cardResult.getNumber()) &&
                            Objects.equals(cardRequest.getMaturity(), cardResult.getMaturity()) &&
                            Objects.equals(cardRequest.getCvv(), cardResult.getCvv()) &&
                            cardResult.getUsage() != 100) {

                        var value = (cardRequest.getValue() * 100) / cardResult.getLimit();

                        cardResult.setUsage((int) (cardResult.getUsage() + value));

                        return cardRepository.save(cardResult)
                                .thenReturn(Boolean.TRUE);
                    } else {
                        return  Mono.just(Boolean.FALSE);
                    }
                });
    }

}
