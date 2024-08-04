package org.example.spring_clone;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.example.spring_clone.annotation.Autowire;
import org.example.spring_clone.annotation.Component;
import org.example.spring_clone.loader.ContextLoader;
import org.example.spring_clone.loader.Runner;
import org.example.spring_clone.service.OrderService;
import org.example.spring_clone.service.RestaurantService;

@Component
@Slf4j
public class Application implements Runner {

    @Autowire
    private OrderService orderService;

    public static void main(String[] args) {
        ContextLoader.getInstance().load("com.datbv.di");
    }

    @Override
    public void run() {
        log.info("Application ready to start");

        orderService.makeOrder();

        val restaurantService = ContextLoader.getInstance()
                .getBean(RestaurantService.class);
        restaurantService.logToday();
    }
}
