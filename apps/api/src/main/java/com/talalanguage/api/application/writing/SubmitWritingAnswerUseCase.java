package com.talalanguage.api.application.writing;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.application.writing.exception.WritingChallengeNotFoundException;
import com.talalanguage.api.application.writing.model.CreatedWritingSubmissionView;
import com.talalanguage.api.application.writing.model.WritingFeedbackView;
import com.talalanguage.api.application.writing.port.WritingChallengeRepository;
import com.talalanguage.api.application.writing.port.WritingFeedbackProvider;
import com.talalanguage.api.application.writing.port.WritingSubmissionRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.domain.writing.WritingChallenge;
import com.talalanguage.api.domain.writing.WritingFeedback;
import com.talalanguage.api.domain.writing.WritingSubmission;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class SubmitWritingAnswerUseCase {

    private final WritingChallengeRepository writingChallengeRepository;
    private final WritingSubmissionRepository writingSubmissionRepository;
    private final WritingFeedbackProvider writingFeedbackProvider;
    private final RegisterLearningActivityUseCase registerLearningActivityUseCase;

    public SubmitWritingAnswerUseCase(
            WritingChallengeRepository writingChallengeRepository,
            WritingSubmissionRepository writingSubmissionRepository,
            WritingFeedbackProvider writingFeedbackProvider,
            RegisterLearningActivityUseCase registerLearningActivityUseCase
    ) {
        this.writingChallengeRepository = writingChallengeRepository;
        this.writingSubmissionRepository = writingSubmissionRepository;
        this.writingFeedbackProvider = writingFeedbackProvider;
        this.registerLearningActivityUseCase = registerLearningActivityUseCase;
    }

    public CreatedWritingSubmissionView execute(Command command) {
        WritingChallenge challenge = writingChallengeRepository.findById(command.challengeId())
                .orElseThrow(WritingChallengeNotFoundException::new);

        WritingFeedback feedback = writingFeedbackProvider.review(challenge, command.content());
        WritingSubmission submission = writingSubmissionRepository.save(
                WritingSubmission.create(
                        UserId.from(command.userId()),
                        challenge.id(),
                        challenge.language(),
                        challenge.level(),
                        command.content(),
                        feedback
                )
        );

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                command.userId(),
                ActivityType.WRITING,
                SkillType.WRITING,
                feedback.score(),
                Instant.now(),
                submission.id()
        ));

        return new CreatedWritingSubmissionView(
                submission.id(),
                submission.status().name(),
                WritingFeedbackView.from(submission.feedback())
        );
    }

    public record Command(
            String userId,
            String challengeId,
            String content
    ) {
    }
}
