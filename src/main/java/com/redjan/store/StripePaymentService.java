package com.redjan.store;

public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount){
        System.out.print("Stripe");
        System.out.print("Amount: " + amount);
    }
}
