package com.talalanguage.api.application.notifications;

import com.talalanguage.api.application.notifications.exception.NotificationNotFoundException;
import com.talalanguage.api.application.notifications.model.NotificationView;
import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class MarkNotificationReadUseCase {

    private final NotificationRepository notificationRepository;

    public MarkNotificationReadUseCase(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationView execute(Command command) {
        var notification = notificationRepository.findByIdAndUserId(
                        command.notificationId(),
                        UserId.from(command.userId())
                )
                .orElseThrow(NotificationNotFoundException::new);

        var saved = notificationRepository.save(notification.markAsRead(Instant.now()));
        return NotificationView.from(saved);
    }

    public record Command(String userId, String notificationId) {
    }
}
