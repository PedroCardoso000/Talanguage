package com.talalanguage.api.application.notifications.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository {

    Notification save(Notification notification);

    List<Notification> findByUserId(UserId userId);

    Optional<Notification> findByIdAndUserId(String id, UserId userId);
}
