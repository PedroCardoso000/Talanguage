package com.talalanguage.api.application.mocktest;

import com.talalanguage.api.application.mocktest.exception.MockTestAttemptNotFoundException;
import com.talalanguage.api.application.mocktest.model.MockTestAttemptView;
import com.talalanguage.api.application.mocktest.port.MockTestAttemptRepository;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetMockTestAttemptUseCase {

    private final MockTestAttemptRepository mockTestAttemptRepository;

    public GetMockTestAttemptUseCase(MockTestAttemptRepository mockTestAttemptRepository) {
        this.mockTestAttemptRepository = mockTestAttemptRepository;
    }

    public MockTestAttemptView execute(Command command) {
        return mockTestAttemptRepository.findByIdAndUserId(command.attemptId(), UserId.from(command.userId()))
                .map(MockTestAttemptView::from)
                .orElseThrow(MockTestAttemptNotFoundException::new);
    }

    public record Command(
            String userId,
            String attemptId
    ) {
    }
}
