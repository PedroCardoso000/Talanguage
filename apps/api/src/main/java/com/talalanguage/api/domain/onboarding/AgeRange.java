package com.talalanguage.api.domain.onboarding;

import java.util.Arrays;

public enum AgeRange {
    UNDER_18("UNDER_18"),
    AGE_18_24("18_24"),
    AGE_25_34("25_34"),
    AGE_35_44("35_44"),
    AGE_45_54("45_54"),
    AGE_55_PLUS("55_PLUS");

    private final String apiValue;

    AgeRange(String apiValue) { this.apiValue = apiValue; }

    public String apiValue() { return apiValue; }

    public static AgeRange fromApiValue(String value) {
        if (value == null || value.isBlank()) { return null; }
        return Arrays.stream(values()).filter(item -> item.apiValue.equals(value))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Age range is invalid."));
    }
}
