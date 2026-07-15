package com.talalanguage.api.application.writing.port;

import com.talalanguage.api.domain.writing.WritingChallenge;
import com.talalanguage.api.domain.writing.WritingLanguage;
import com.talalanguage.api.domain.writing.WritingLevel;
import java.util.List;
import java.util.Optional;

public interface WritingChallengeRepository {

    List<WritingChallenge> listActive(Optional<WritingLanguage> language, Optional<WritingLevel> level);

    Optional<WritingChallenge> findById(String challengeId);
}
