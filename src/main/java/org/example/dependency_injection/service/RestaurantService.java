package org.example.dependency_injection.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dependency_injection.annotation.Component;

import java.time.Instant;

@Slf4j
@Component
public class RestaurantService {

    public void doSomething() {
        log.info("Restaurant service does something");
    }

    public void logToday() {
        log.info("Today: {}", Instant.now());
    }

}