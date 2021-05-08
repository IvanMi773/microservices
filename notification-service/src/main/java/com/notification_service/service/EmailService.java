package com.notification_service.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail (String message) {
        System.out.println("Message: " + message);
    }
}
