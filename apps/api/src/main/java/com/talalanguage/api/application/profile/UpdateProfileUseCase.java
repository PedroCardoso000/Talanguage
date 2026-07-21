package com.talalanguage.api.application.profile;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.application.profile.model.ProfileView;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.NotificationType;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UpdateProfileUseCase {

    private final UserRepository userRepository;
    private final CreateNotificationUseCase createNotificationUseCase;

    public UpdateProfileUseCase(UserRepository userRepository, CreateNotificationUseCase createNotificationUseCase) {
        this.userRepository = userRepository;
        this.createNotificationUseCase = createNotificationUseCase;
    }

    public ProfileView execute(Command command) {
        var existingUser = userRepository.findById(UserId.from(command.userId()))
                .orElseThrow(AuthenticationRequiredException::new);

        var updatedUser = existingUser.updateProfile(
                command.name(),
                command.targetLanguage(),
                command.currentLevel(),
                command.studyGoal(),
                command.avatarUrl()
        );

        userRepository.save(updatedUser);
        createNotificationUseCase.execute(new CreateNotificationUseCase.Command(
                updatedUser.id().value(),
                NotificationType.PROFILE_UPDATED,
                profileDeduplicationKey(command),
                "Perfil atualizado",
                "Seus dados de estudo foram salvos. Agora o produto para de adivinhar seu contexto.",
                "/profile"
        ));
        return ProfileView.from(updatedUser);
    }

    private String profileDeduplicationKey(Command command) {
        String profileState = String.join("\u001f",
                command.name(), command.targetLanguage(), command.currentLevel(), command.studyGoal(),
                command.avatarUrl() == null ? "" : command.avatarUrl());
        return "profile:" + UUID.nameUUIDFromBytes(profileState.getBytes(StandardCharsets.UTF_8));
    }

    public record Command(
            String userId,
            String name,
            String targetLanguage,
            String currentLevel,
            String studyGoal,
            String avatarUrl
    ) {
    }
}
