package com.talalanguage.api.web.notifications;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.notifications.ListNotificationsUseCase;
import com.talalanguage.api.application.notifications.MarkNotificationReadUseCase;
import com.talalanguage.api.web.notifications.dto.NotificationResponseDto;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {

    private final ListNotificationsUseCase listNotificationsUseCase;
    private final MarkNotificationReadUseCase markNotificationReadUseCase;

    public NotificationsController(
            ListNotificationsUseCase listNotificationsUseCase,
            MarkNotificationReadUseCase markNotificationReadUseCase
    ) {
        this.listNotificationsUseCase = listNotificationsUseCase;
        this.markNotificationReadUseCase = markNotificationReadUseCase;
    }

    @GetMapping
    public List<NotificationResponseDto> list(Authentication authentication) {
        return listNotificationsUseCase.execute(new ListNotificationsUseCase.Command(requireUserId(authentication)))
                .stream()
                .map(NotificationResponseDto::from)
                .toList();
    }

    @PatchMapping("/{id}/read")
    public NotificationResponseDto markAsRead(Authentication authentication, @PathVariable String id) {
        return NotificationResponseDto.from(markNotificationReadUseCase.execute(
                new MarkNotificationReadUseCase.Command(requireUserId(authentication), id)
        ));
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
