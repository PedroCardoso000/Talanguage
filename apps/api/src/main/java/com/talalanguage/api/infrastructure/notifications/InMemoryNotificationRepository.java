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
    public Notification save(Notification notification) {
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

    public void clear() {
        notificationsById.clear();
    }
}
