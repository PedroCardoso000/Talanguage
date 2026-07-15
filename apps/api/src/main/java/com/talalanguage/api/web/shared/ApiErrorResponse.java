package com.talalanguage.api.web.shared;

import java.util.List;

public record ApiErrorResponse(
        ErrorBody error
) {
    public record ErrorBody(
            String code,
            String message,
            List<ErrorDetail> details
    ) {
    }

    public record ErrorDetail(
            String field,
            String message
    ) {
    }
}
