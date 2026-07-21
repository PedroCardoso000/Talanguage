package com.talalanguage.api.infrastructure.notifications;

import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryNotificationRepository implements NotificationRepository {

    private final Map<String, Notification> notificationsById = new ConcurrentHashMap<>();

    @Override
    public synchronized Notification save(Notification notification) {
        var existing = findByUserIdAndTypeAndDeduplicationKey(
                notification.userId(), notification.type(), notification.deduplicationKey());
        if (existing.isPresent() && !existing.get().id().equals(notification.id())) {
            return existing.get();
        }
        notificationsById.put(notification.id(), notification);
        return notification;
    }

    @Override
    public List<Notification> findByUserId(UserId userId) {
        return notificationsById.values().stream()
                .filter(notification -> notification.userId().equals(userId))
                .toList();
    }

    @Override
    public Optional<Notification> findByIdAndUserId(String id, UserId userId) {
        return Optional.ofNullable(notificationsById.get(id))
                .filter(notification -> notification.userId().equals(userId));
    }

    @Override
    public Optional<Notification> findByUserIdAndTypeAndDeduplicationKey(
            UserId userId,
            com.talalanguage.api.domain.notifications.NotificationType type,
            String deduplicationKey
    ) {
        return notificationsById.values().stream()
                .filter(notification -> notification.userId().equals(userId))
                .filter(notification -> notification.type() == type)
                .filter(notification -> notification.deduplicationKey().equals(deduplicationKey))
                .findFirst();
    }

    public void clear() {
        notificationsById.clear();
    }
}
