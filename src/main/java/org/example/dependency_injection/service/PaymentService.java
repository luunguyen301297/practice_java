package org.example.dependency_injection.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dependency_injection.annotation.Component;

@Slf4j
@Component
public class PaymentService {

    public void doSomething() {
        log.info("Payment service does something");
    }
}
