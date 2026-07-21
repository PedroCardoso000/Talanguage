package com.talalanguage.api.infrastructure.notifications;

import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import com.talalanguage.api.infrastructure.persistence.entity.NotificationEntity;
import com.talalanguage.api.infrastructure.persistence.repository.NotificationJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
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
        var existing = findByUserIdAndTypeAndDeduplicationKey(
                notification.userId(), notification.type(), notification.deduplicationKey());
        if (existing.isPresent() && !existing.get().id().equals(notification.id())) {
            return existing.get();
        }
        try {
            notificationJpaRepository.saveAndFlush(toEntity(notification));
            return notification;
        } catch (DataIntegrityViolationException exception) {
            return findByUserIdAndTypeAndDeduplicationKey(
                    notification.userId(), notification.type(), notification.deduplicationKey())
                    .orElseThrow(() -> exception);
        }
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

    @Override
    public Optional<Notification> findByUserIdAndTypeAndDeduplicationKey(
            UserId userId,
            com.talalanguage.api.domain.notifications.NotificationType type,
            String deduplicationKey
    ) {
        return notificationJpaRepository.findByUserIdAndTypeAndDeduplicationKey(
                userId.value(), type.name(), deduplicationKey).map(this::toDomain);
    }

    private NotificationEntity toEntity(Notification notification) {
        return new NotificationEntity(
                notification.id(),
                notification.userId().value(),
                notification.type().name(),
                notification.deduplicationKey(),
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
                com.talalanguage.api.domain.notifications.NotificationType.valueOf(entity.getType()),
                entity.getDeduplicationKey(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getActionRoute(),
                entity.getCreatedAt(),
                entity.getReadAt()
        );
    }
}
