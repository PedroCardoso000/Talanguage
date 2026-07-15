package com.talalanguage.api.application.community.port;

import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.domain.community.PracticePartnerProfile;
import java.util.List;
import java.util.Optional;

public interface PracticePartnerQueryService {

    List<PracticePartnerProfile> findSuggested(Optional<CommunityLanguage> language);

    boolean existsById(String id);
}
