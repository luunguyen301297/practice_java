package org.example.dependency_injection;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.example.dependency_injection.annotation.Autowire;
import org.example.dependency_injection.annotation.Component;
import org.example.dependency_injection.loader.ContextLoader;
import org.example.dependency_injection.loader.Runner;
import org.example.dependency_injection.service.OrderService;
import org.example.dependency_injection.service.RestaurantService;

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
