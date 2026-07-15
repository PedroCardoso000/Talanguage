package com.talalanguage.api.application.notifications;

import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import org.springframework.stereotype.Service;

@Service
public class CreateNotificationUseCase {

    private final NotificationRepository notificationRepository;

    public CreateNotificationUseCase(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void execute(Command command) {
        notificationRepository.save(Notification.create(
                UserId.from(command.userId()),
                command.title(),
                command.message(),
                command.actionRoute()
        ));
    }

    public record Command(
            String userId,
            String title,
            String message,
            String actionRoute
    ) {
    }
}
