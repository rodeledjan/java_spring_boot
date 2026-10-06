package com.redjan.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

//@Service("stripe")
//@Primary
public class StripePaymentService implements PaymentService {

    @Value("${stripe.apiUrl}")
    private String apiUrl;
    @Value("${stripe.timeout:3000}") //setting a default
    private int timeout;
    @Value("${stripe.enabled}")
    private boolean enabled;
    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount){
        System.out.println("Stripe");
        System.out.println("Amount: " + amount);
        System.out.println("apiUrl: " + apiUrl);
        System.out.println("Timeout: " + timeout);
        System.out.println("Enabled: " + enabled);
        System.out.println("Supported currencies: " + supportedCurrencies);

    }
}
