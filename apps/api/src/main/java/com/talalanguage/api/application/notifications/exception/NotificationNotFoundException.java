package com.talalanguage.api.application.notifications.exception;

public class NotificationNotFoundException extends RuntimeException {

    public NotificationNotFoundException() {
        super("Notification was not found.");
    }
}
