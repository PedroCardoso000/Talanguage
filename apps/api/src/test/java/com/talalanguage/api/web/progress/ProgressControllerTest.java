package com.talalanguage.api.web.progress;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
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
class ProgressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryLearningActivityRepository learningActivityRepository;

    @Autowired
    private RegisterLearningActivityUseCase registerLearningActivityUseCase;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        learningActivityRepository.clear();
    }

    @Test
    void shouldReturnSummary() throws Exception {
        String token = registerAndAuthenticate();
        String userId = currentUserId(token);

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                userId,
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                75,
                null,
                "speak-1"
        ));

        mockMvc.perform(get("/api/progress/summary")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.streakDays").value(1))
                .andExpect(jsonPath("$.longestStreakDays").value(1))
                .andExpect(jsonPath("$.lastActivityDate").isNotEmpty())
                .andExpect(jsonPath("$.totalActivities").value(1))
                .andExpect(jsonPath("$.skillProgress.speaking").value(75))
                .andExpect(jsonPath("$.activityTotals.daysPracticed").value(1))
                .andExpect(jsonPath("$.activityTotals.speakingSessions").value(1))
                .andExpect(jsonPath("$.activityTotals.flashcardReviews").value(0));
    }

    @Test
    void shouldReturnActivities() throws Exception {
        String token = registerAndAuthenticate();
        String userId = currentUserId(token);

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                userId,
                ActivityType.WRITING,
                SkillType.WRITING,
                80,
                null,
                "write-1"
        ));

        mockMvc.perform(get("/api/progress/activities")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("WRITING"));
    }

    @Test
    void shouldReturnWeeklySummary() throws Exception {
        String token = registerAndAuthenticate();
        String userId = currentUserId(token);

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                userId,
                ActivityType.FLASHCARDS,
                SkillType.VOCABULARY,
                88,
                null,
                "flash-1"
        ));

        mockMvc.perform(get("/api/progress/weekly-summary")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activitiesCompleted").value(1))
                .andExpect(jsonPath("$.skillProgress.vocabulary").value(88))
                .andExpect(jsonPath("$.dailyActivityCounts.length()").value(7));
    }

    @Test
    void shouldRejectUnauthenticatedRequests() throws Exception {
        mockMvc.perform(get("/api/progress/summary"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/progress/activities"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/progress/weekly-summary"))
                .andExpect(status().isUnauthorized());
    }

    private String registerAndAuthenticate() throws Exception {
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
        return payload.get("accessToken").asText();
    }

    private String currentUserId(String token) throws Exception {
        MvcResult result = mockMvc.perform(get("/api/auth/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asText();
    }
}
