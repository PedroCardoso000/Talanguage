package com.talalanguage.api.application.community;

import com.talalanguage.api.application.community.model.PracticePartnerView;
import com.talalanguage.api.application.community.port.PracticePartnerQueryService;
import com.talalanguage.api.domain.community.CommunityLanguage;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ListPracticePartnersUseCase {

    private final PracticePartnerQueryService practicePartnerQueryService;

    public ListPracticePartnersUseCase(PracticePartnerQueryService practicePartnerQueryService) {
        this.practicePartnerQueryService = practicePartnerQueryService;
    }

    public List<PracticePartnerView> execute(Command command) {
        return practicePartnerQueryService.findSuggested(command.language()).stream()
                .map(PracticePartnerView::from)
                .toList();
    }

    public record Command(Optional<CommunityLanguage> language) {
    }
}
