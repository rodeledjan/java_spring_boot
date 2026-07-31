package com.redjan.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        var orderService = new OrderService(new StripePaymentService());

        orderService.placeOrder();

    }
}