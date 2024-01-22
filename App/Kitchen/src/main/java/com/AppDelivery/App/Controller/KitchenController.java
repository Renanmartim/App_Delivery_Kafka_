package com.AppDelivery.App.Controller;

import com.AppDelivery.App.KitchenService.KitchenListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class KitchenController {

    @Autowired
    public KitchenListener consumer;

}
