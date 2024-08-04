package org.example.spring_clone.service;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_clone.annotation.Component;

@Slf4j
@Component
public class PaymentService {

    public void doSomething() {
        log.info("Payment service does something");
    }
}
