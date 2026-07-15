package com.talalanguage.api.infrastructure.notifications;

import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import com.talalanguage.api.infrastructure.persistence.entity.NotificationEntity;
import com.talalanguage.api.infrastructure.persistence.repository.NotificationJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaNotificationRepositoryAdapter implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;

    public JpaNotificationRepositoryAdapter(NotificationJpaRepository notificationJpaRepository) {
        this.notificationJpaRepository = notificationJpaRepository;
    }

    @Override
    public Notification save(Notification notification) {
        notificationJpaRepository.save(toEntity(notification));
        return notification;
    }

    @Override
    public List<Notification> findByUserId(UserId userId) {
        return notificationJpaRepository.findByUserIdOrderByCreatedAtDesc(userId.value())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Notification> findByIdAndUserId(String id, UserId userId) {
        return notificationJpaRepository.findByIdAndUserId(id, userId.value()).map(this::toDomain);
    }

    private NotificationEntity toEntity(Notification notification) {
        return new NotificationEntity(
                notification.id(),
                notification.userId().value(),
                notification.title(),
                notification.message(),
                notification.actionRoute(),
                notification.createdAt(),
                notification.readAt()
        );
    }

    private Notification toDomain(NotificationEntity entity) {
        return Notification.restore(
                entity.getId(),
                UserId.from(entity.getUserId()),
                entity.getTitle(),
                entity.getMessage(),
                entity.getActionRoute(),
                entity.getCreatedAt(),
                entity.getReadAt()
        );
    }
}
