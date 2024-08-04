package org.example.spring_clone.service;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_clone.annotation.Component;

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