package org.example.dependency_injection.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dependency_injection.annotation.Autowire;
import org.example.dependency_injection.annotation.Component;
import org.example.dependency_injection.annotation.PostConstruct;

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
