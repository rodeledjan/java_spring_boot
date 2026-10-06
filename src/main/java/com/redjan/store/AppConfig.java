package com.redjan.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${payment-service:stripe}")
    private String paymentService;

    @Bean
    public PaymentService stripe(){ //bean should be a noun, for ex. stripe
        //you can put logic here like
        //if..., return new PaypalService
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal(){
        return new PayPalPaymentService();
    }

    @Bean
    public OrderService orderService(){

        if(paymentService.equals("stripe")){
            return new OrderService(stripe());
        }
        return new OrderService(paypal());
    }
}
