package com.talalanguage.api.application.community.port;

import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.domain.community.CommunityLevel;
import com.talalanguage.api.domain.community.PracticeGroup;
import java.util.List;
import java.util.Optional;

public interface PracticeGroupRepository {

    List<PracticeGroup> findActive(Optional<CommunityLanguage> language, Optional<CommunityLevel> level);

    boolean existsById(String id);
}
