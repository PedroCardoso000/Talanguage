package com.talalanguage.api.infrastructure.writing;

import com.talalanguage.api.application.writing.port.WritingChallengeRepository;
import com.talalanguage.api.domain.writing.WritingChallenge;
import com.talalanguage.api.domain.writing.WritingLanguage;
import com.talalanguage.api.domain.writing.WritingLevel;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryWritingChallengeRepository implements WritingChallengeRepository {

    private static final List<WritingChallenge> CHALLENGES = List.of(
            new WritingChallenge(
                    "follow-up-email",
                    WritingLanguage.ENGLISH,
                    WritingLevel.BEGINNER,
                    "Write a short follow-up email after a meeting and reinforce your interest in continuing the conversation.",
                    "Professional tone and clarity.",
                    true
            ),
            new WritingChallenge(
                    "trip-story",
                    WritingLanguage.ENGLISH,
                    WritingLevel.INTERMEDIATE,
                    "Describe a memorable trip in 6 to 8 sentences, including the place, your emotions, and what you learned.",
                    "Narrative flow and descriptive vocabulary.",
                    true
            ),
            new WritingChallenge(
                    "opinion",
                    WritingLanguage.ENGLISH,
                    WritingLevel.ADVANCED,
                    "Defend your opinion about studying languages every day in a short text with an opening, one argument, and a closing.",
                    "Cohesion and organization.",
                    true
            )
    );

    @Override
    public List<WritingChallenge> listActive(Optional<WritingLanguage> language, Optional<WritingLevel> level) {
        return CHALLENGES.stream()
                .filter(WritingChallenge::active)
                .filter(challenge -> language.map(value -> challenge.language() == value).orElse(true))
                .filter(challenge -> level.map(value -> challenge.level() == value).orElse(true))
                .toList();
    }

    @Override
    public Optional<WritingChallenge> findById(String challengeId) {
        return CHALLENGES.stream()
                .filter(challenge -> challenge.id().equals(challengeId))
                .findFirst();
    }
}
