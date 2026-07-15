package com.talalanguage.api.web.community.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterCommunityInterestRequestDto(
        @NotBlank(message = "Target type is required.") String targetType,
        @NotBlank(message = "Target ID is required.") String targetId
) {
}
