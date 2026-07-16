package com.talalanguage.api.web.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.auth.InMemoryPasswordResetNotifier;
import com.talalanguage.api.infrastructure.auth.InMemoryPasswordResetTokenRepository;
import com.talalanguage.api.infrastructure.auth.SecurePasswordResetTokenGenerator;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.PasswordResetToken;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryPasswordResetNotifier passwordResetNotifier;

    @Autowired
    private InMemoryPasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private SecurePasswordResetTokenGenerator passwordResetTokenGenerator;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        passwordResetNotifier.clear();
        passwordResetTokenRepository.clear();
    }

    @Test
    void shouldRegisterUser() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "email": "pedro@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user.id").isNotEmpty())
                .andExpect(jsonPath("$.user.name").value("Pedro Cardoso"))
                .andExpect(jsonPath("$.user.email").value("pedro@example.com"))
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

    @Test
    void shouldLoginUser() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Pedro Cardoso",
                          "email": "pedro@example.com",
                          "password": "StrongPassword123"
                        }
                        """));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "pedro@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value("pedro@example.com"))
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

    @Test
    void shouldReturnAuthenticatedUser() throws Exception {
        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "email": "pedro@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode payload = objectMapper.readTree(registerResult.getResponse().getContentAsString());
        String accessToken = payload.get("accessToken").asText();

        mockMvc.perform(get("/api/auth/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("pedro@example.com"))
                .andExpect(jsonPath("$.name").value("Pedro Cardoso"));
    }

    @Test
    void shouldRejectUnauthenticatedMe() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error.code").value("UNAUTHORIZED"));
    }

    @Test
    void shouldRejectDuplicateEmail() throws Exception {
        String payload = """
                {
                  "name": "Pedro Cardoso",
                  "email": "pedro@example.com",
                  "password": "StrongPassword123"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error.code").value("EMAIL_ALREADY_IN_USE"));
    }

    @Test
    void shouldLogoutAndInvalidateSession() throws Exception {
        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "email": "pedro@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode payload = objectMapper.readTree(registerResult.getResponse().getContentAsString());
        String accessToken = payload.get("accessToken").asText();

        mockMvc.perform(post("/api/auth/logout")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/auth/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnSameGenericResponseForExistingAndUnknownEmail() throws Exception {
        register("reset-existing@example.com", "OldPassword123");

        String existingResponse = forgotPassword("reset-existing@example.com");
        String unknownResponse = forgotPassword("reset-unknown@example.com");

        org.junit.jupiter.api.Assertions.assertEquals(existingResponse, unknownResponse);
        org.junit.jupiter.api.Assertions.assertFalse(existingResponse.contains("reset-existing@example.com"));
    }

    @Test
    void shouldResetPasswordOnceAndInvalidatePreviousSessions() throws Exception {
        String oldSession = register("reset-success@example.com", "OldPassword123");
        forgotPassword("reset-success@example.com");
        String resetToken = passwordResetNotifier.tokenFor("reset-success@example.com").orElseThrow();

        mockMvc.perform(post("/api/auth/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                java.util.Map.of("token", resetToken, "newPassword", "NewPassword456"))))
                .andExpect(status().isNoContent());

        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"reset-success@example.com\",\"password\":\"OldPassword123\"}"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"reset-success@example.com\",\"password\":\"NewPassword456\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/auth/me").header(HttpHeaders.AUTHORIZATION, "Bearer " + oldSession))
                .andExpect(status().isUnauthorized());
        reset(resetToken, "AnotherPassword789").andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("PASSWORD_RESET_TOKEN_INVALID"));
    }

    @Test
    void shouldRejectInvalidExpiredAndWeakResetRequestsWithoutExposingSecrets() throws Exception {
        reset("invalid-sensitive-token", "ValidPassword123")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("PASSWORD_RESET_TOKEN_INVALID"))
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(jsonPath("$.password").doesNotExist());

        register("reset-expired@example.com", "OldPassword123");
        var user = userRepository.findByEmail(Email.of("reset-expired@example.com")).orElseThrow();
        String expiredRawToken = "expired-sensitive-token";
        Instant createdAt = Instant.now().minus(30, ChronoUnit.MINUTES);
        passwordResetTokenRepository.save(new PasswordResetToken(UUID.randomUUID().toString(), user.id(),
                passwordResetTokenGenerator.hash(expiredRawToken), createdAt.plus(15, ChronoUnit.MINUTES), null, createdAt));

        reset(expiredRawToken, "ValidPassword123").andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("PASSWORD_RESET_TOKEN_INVALID"));

        forgotPassword("reset-expired@example.com");
        String validToken = passwordResetNotifier.tokenFor("reset-expired@example.com").orElseThrow();
        reset(validToken, "weak").andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("VALIDATION_ERROR"));
    }

    @Test
    void shouldStoreOnlyPasswordResetTokenHash() throws Exception {
        register("reset-hash@example.com", "OldPassword123");
        forgotPassword("reset-hash@example.com");
        String rawToken = passwordResetNotifier.tokenFor("reset-hash@example.com").orElseThrow();
        org.junit.jupiter.api.Assertions.assertFalse(passwordResetTokenRepository.containsRawToken(rawToken));
        org.junit.jupiter.api.Assertions.assertTrue(passwordResetTokenRepository
                .findByTokenHashForUpdate(passwordResetTokenGenerator.hash(rawToken)).isPresent());
    }

    private String register(String email, String password) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Reset User\",\"email\":\"" + email
                                + "\",\"password\":\"" + password + "\"}"))
                .andExpect(status().isCreated()).andReturn();
        return objectMapper.readTree(result.getResponse().getContentAsString()).get("accessToken").asText();
    }

    private String forgotPassword(String email) throws Exception {
        return mockMvc.perform(post("/api/auth/forgot-password").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"" + email + "\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value(
                        "If the email is registered, password reset instructions will be sent."))
                .andReturn().getResponse().getContentAsString();
    }

    private org.springframework.test.web.servlet.ResultActions reset(String token, String password) throws Exception {
        return mockMvc.perform(post("/api/auth/reset-password").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(java.util.Map.of("token", token, "newPassword", password))));
    }
}
