package com.redjan.store;

import org.springframework.stereotype.Component;

public interface PaymentService {
    void processPayment(double amount);
}
