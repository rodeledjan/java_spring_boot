package com.redjan.store;

public class PayPalPaymentService  implements PaymentService{


    @Override
    public void processPayment(double amount) {
        System.out.print("Paypal");
        System.out.print("Amount: " + amount);
    }
}
