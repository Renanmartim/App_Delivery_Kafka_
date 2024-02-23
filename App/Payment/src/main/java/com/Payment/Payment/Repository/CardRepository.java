package com.Payment.Payment.Repository;

import com.Payment.Payment.Model.CardModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CardRepository extends ReactiveMongoRepository<CardModel, String> {
    Mono<CardModel> findByNumber(String number);
}
