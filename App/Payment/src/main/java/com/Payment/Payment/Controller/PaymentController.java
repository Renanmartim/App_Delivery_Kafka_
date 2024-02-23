package com.Payment.Payment.Controller;

import com.Payment.Payment.Model.CardModel;
import com.Payment.Payment.Request.CardRequest;
import com.Payment.Payment.Service.PaymentService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    private PaymentService paymentService;

    private PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/save")
    public Mono<CardModel> save(@RequestBody CardModel cardModel){

        System.out.println(cardModel);

        return paymentService.save(cardModel);

    }

    @PostMapping("/pay")
    public Mono<String> pay(@RequestBody CardRequest cardRequest){

        return paymentService.payment(cardRequest);

    }

    @GetMapping("/oi")
    public Mono<String> test(){

        return Mono.just("oi");

    }

}
