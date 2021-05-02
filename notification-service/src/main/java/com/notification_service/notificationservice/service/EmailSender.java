package com.notification_service.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    public void sendEmail(String message) {
        System.out.println("Send email with message: " + message);
    }
}
