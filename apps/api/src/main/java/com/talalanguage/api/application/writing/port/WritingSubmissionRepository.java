package com.talalanguage.api.application.writing.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.writing.WritingSubmission;
import java.util.List;

public interface WritingSubmissionRepository {

    WritingSubmission save(WritingSubmission submission);

    List<WritingSubmission> listRecentByUserId(UserId userId, int limit);
}
