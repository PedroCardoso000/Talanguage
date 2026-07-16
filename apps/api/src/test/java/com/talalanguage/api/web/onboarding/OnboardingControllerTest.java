package com.talalanguage.api.web.onboarding;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.onboarding.InMemoryLearnerOnboardingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OnboardingControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired InMemoryUserRepository userRepository;
    @Autowired InMemorySessionService sessionService;
    @Autowired InMemoryLearnerOnboardingRepository onboardingRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear(); sessionService.clear(); onboardingRepository.clear();
    }

    @Test
    void shouldRequireAuthentication() throws Exception {
        mockMvc.perform(get("/api/onboarding/me")).andExpect(status().isUnauthorized());
        mockMvc.perform(put("/api/onboarding/me").contentType(MediaType.APPLICATION_JSON).content(validPayload("CAREER")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldSaveUpdateAndMarkOnboardingAsCompleted() throws Exception {
        String token = register("onboarding@example.com");
        mockMvc.perform(get("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(token)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.completed").value(false));
        mockMvc.perform(get("/api/auth/me").header(HttpHeaders.AUTHORIZATION, bearer(token)))
                .andExpect(jsonPath("$.onboardingCompleted").value(false));

        mockMvc.perform(put("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .contentType(MediaType.APPLICATION_JSON).content(validPayload("CAREER")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.completed").value(true))
                .andExpect(jsonPath("$.onboarding.completedAt").isNotEmpty());

        mockMvc.perform(put("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .contentType(MediaType.APPLICATION_JSON).content(validPayload("TRAVEL")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.onboarding.learningMotivations[0]").value("TRAVEL"));
        org.junit.jupiter.api.Assertions.assertEquals(1, onboardingRepository.size());
        mockMvc.perform(get("/api/auth/me").header(HttpHeaders.AUTHORIZATION, bearer(token)))
                .andExpect(jsonPath("$.onboardingCompleted").value(true));
    }

    @Test
    void shouldRejectInvalidControlledValuesDuplicatesAndMissingOtherDescription() throws Exception {
        String token = register("invalid-onboarding@example.com");
        mockMvc.perform(put("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .contentType(MediaType.APPLICATION_JSON).content(validPayload("INVALID")))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .contentType(MediaType.APPLICATION_JSON).content(validPayload("CAREER").replace(
                                "[\"CAREER\"]", "[\"CAREER\",\"CAREER\"]")))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .contentType(MediaType.APPLICATION_JSON).content(validPayload("CAREER")
                                .replace("\"EMPLOYED\"", "\"OTHER\"")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldKeepUsersIsolated() throws Exception {
        String first = register("first-onboarding@example.com");
        String second = register("second-onboarding@example.com");
        mockMvc.perform(put("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(first))
                .contentType(MediaType.APPLICATION_JSON).content(validPayload("CAREER"))).andExpect(status().isOk());
        mockMvc.perform(get("/api/onboarding/me").header(HttpHeaders.AUTHORIZATION, bearer(second)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.completed").value(false));
    }

    private String register(String email) throws Exception {
        var result = mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Student User\",\"email\":\"" + email
                                + "\",\"password\":\"StrongPassword123\"}"))
                .andExpect(status().isCreated()).andReturn();
        return objectMapper.readTree(result.getResponse().getContentAsString()).get("accessToken").asText();
    }

    private String validPayload(String motivation) {
        return """
                {"ageRange":"25_34","occupation":"EMPLOYED","occupationDescription":null,
                "learningMotivations":["%s"],"primaryGoal":"Participate in meetings",
                "difficultySkills":["SPEAKING"],"currentLevel":"INTERMEDIATE","weeklyAvailabilityMinutes":180}
                """.formatted(motivation);
    }

    private String bearer(String token) { return "Bearer " + token; }
}
