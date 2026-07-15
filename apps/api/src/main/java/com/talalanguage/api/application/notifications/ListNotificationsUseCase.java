package com.talalanguage.api.application.notifications;

import com.talalanguage.api.application.notifications.model.NotificationView;
import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.domain.auth.UserId;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListNotificationsUseCase {

    private final NotificationRepository notificationRepository;

    public ListNotificationsUseCase(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationView> execute(Command command) {
        return notificationRepository.findByUserId(UserId.from(command.userId()))
                .stream()
                .sorted(Comparator.comparing(notification -> notification.createdAt(), Comparator.reverseOrder()))
                .limit(20)
                .map(NotificationView::from)
                .toList();
    }

    public record Command(String userId) {
    }
}
