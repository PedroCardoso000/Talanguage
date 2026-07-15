package com.talalanguage.api.application.profile;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.application.profile.model.ProfileView;
import com.talalanguage.api.domain.auth.UserId;
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
                "Perfil atualizado",
                "Seus dados de estudo foram salvos. Agora o produto para de adivinhar seu contexto.",
                "/profile"
        ));
        return ProfileView.from(updatedUser);
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
