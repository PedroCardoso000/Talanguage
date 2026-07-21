package com.talalanguage.api.application.notifications.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository {

    Notification save(Notification notification);

    List<Notification> findByUserId(UserId userId);

    Optional<Notification> findByIdAndUserId(String id, UserId userId);

    default Optional<Notification> findByUserIdAndTypeAndDeduplicationKey(
            UserId userId,
            com.talalanguage.api.domain.notifications.NotificationType type,
            String deduplicationKey
    ) {
        return findByUserId(userId).stream()
                .filter(notification -> notification.type() == type)
                .filter(notification -> notification.deduplicationKey().equals(deduplicationKey))
                .findFirst();
    }
}
