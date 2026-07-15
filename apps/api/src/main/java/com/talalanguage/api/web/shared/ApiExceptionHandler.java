package com.talalanguage.api.web.shared;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.community.exception.CommunityTargetNotFoundException;
import com.talalanguage.api.application.auth.exception.EmailAlreadyInUseException;
import com.talalanguage.api.application.auth.exception.InvalidCredentialsException;
import com.talalanguage.api.application.auth.exception.WeakPasswordException;
import com.talalanguage.api.application.flashcards.exception.FlashcardNotFoundException;
import com.talalanguage.api.application.mocktest.exception.MockTestAttemptNotFoundException;
import com.talalanguage.api.application.mocktest.exception.MockTestNotFoundException;
import com.talalanguage.api.application.notifications.exception.NotificationNotFoundException;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionAlreadyFinishedException;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionNotFoundException;
import com.talalanguage.api.application.speaking.exception.SpeakingTopicNotFoundException;
import com.talalanguage.api.application.writing.exception.WritingChallengeNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
        List<ApiErrorResponse.ErrorDetail> details = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toErrorDetail)
                .toList();

        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                new ApiErrorResponse.ErrorBody("VALIDATION_ERROR", "Invalid request data.", details)
        ));
    }

    @ExceptionHandler({IllegalArgumentException.class, WeakPasswordException.class})
    public ResponseEntity<ApiErrorResponse> handleBadRequest(RuntimeException exception) {
        return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", exception.getMessage(), List.of());
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ApiErrorResponse> handleConflict(EmailAlreadyInUseException exception) {
        return build(HttpStatus.CONFLICT, "EMAIL_ALREADY_IN_USE", exception.getMessage(), List.of());
    }

    @ExceptionHandler({InvalidCredentialsException.class, AuthenticationRequiredException.class})
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(RuntimeException exception) {
        return build(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", exception.getMessage(), List.of());
    }

    @ExceptionHandler(FlashcardNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(FlashcardNotFoundException exception) {
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", exception.getMessage(), List.of());
    }

    @ExceptionHandler({
            WritingChallengeNotFoundException.class,
            SpeakingTopicNotFoundException.class,
            SpeakingSessionNotFoundException.class,
            CommunityTargetNotFoundException.class,
            NotificationNotFoundException.class,
            MockTestNotFoundException.class,
            MockTestAttemptNotFoundException.class
    })
    public ResponseEntity<ApiErrorResponse> handleNotFound(RuntimeException exception) {
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", exception.getMessage(), List.of());
    }

    @ExceptionHandler(SpeakingSessionAlreadyFinishedException.class)
    public ResponseEntity<ApiErrorResponse> handleSpeakingSessionConflict(SpeakingSessionAlreadyFinishedException exception) {
        return build(HttpStatus.CONFLICT, "SESSION_ALREADY_FINISHED", exception.getMessage(), List.of());
    }

    private ApiErrorResponse.ErrorDetail toErrorDetail(FieldError fieldError) {
        return new ApiErrorResponse.ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage());
    }

    private ResponseEntity<ApiErrorResponse> build(
            HttpStatus status,
            String code,
            String message,
            List<ApiErrorResponse.ErrorDetail> details
    ) {
        return ResponseEntity.status(status)
                .body(new ApiErrorResponse(new ApiErrorResponse.ErrorBody(code, message, details)));
    }
}
