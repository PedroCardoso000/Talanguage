package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.port.SessionService;
import org.springframework.stereotype.Service;

@Service
public class LogoutUserUseCase {

    private final SessionService sessionService;

    public LogoutUserUseCase(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void execute(Command command) {
        sessionService.invalidate(command.accessToken());
    }

    public record Command(String accessToken) {
    }
}
