package com.redjan.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email Notification: " + message);
    }
}
