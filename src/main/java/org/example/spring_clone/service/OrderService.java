package org.example.spring_clone.service;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_clone.annotation.Autowire;
import org.example.spring_clone.annotation.Component;
import org.example.spring_clone.annotation.PostConstruct;

@Slf4j
@Component
public class OrderService {

    @Autowire
    private PaymentService paymentService;

    @Autowire
    private RestaurantService restaurantService;

    @PostConstruct
    void postInitiate() {
        log.info("Do something after creating orderService instance");
    }

    public void makeOrder() {
        paymentService.doSomething();
        restaurantService.doSomething();
    }
}
