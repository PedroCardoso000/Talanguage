package com.talalanguage.api.application.community;

import com.talalanguage.api.application.community.model.PracticeGroupView;
import com.talalanguage.api.application.community.port.PracticeGroupRepository;
import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.domain.community.CommunityLevel;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ListPracticeGroupsUseCase {

    private final PracticeGroupRepository practiceGroupRepository;

    public ListPracticeGroupsUseCase(PracticeGroupRepository practiceGroupRepository) {
        this.practiceGroupRepository = practiceGroupRepository;
    }

    public List<PracticeGroupView> execute(Command command) {
        return practiceGroupRepository.findActive(command.language(), command.level()).stream()
                .map(PracticeGroupView::from)
                .toList();
    }

    public record Command(Optional<CommunityLanguage> language, Optional<CommunityLevel> level) {
    }
}
