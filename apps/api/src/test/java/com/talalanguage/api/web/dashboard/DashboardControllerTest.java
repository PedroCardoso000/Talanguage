package com.talalanguage.api.web.dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository learningActivityRepository;

    @Autowired
    private RegisterLearningActivityUseCase registerLearningActivityUseCase;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        learningActivityRepository.clear();
    }

    @Test
    void shouldReturnSummaryForAuthenticatedUser() throws Exception {
        String token = registerAndAuthenticate();
        String userId = currentUserId(token);

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                userId,
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                74,
                null,
                "speak-1"
        ));

        MvcResult dashboardResult = mockMvc.perform(get("/api/dashboard/summary")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Pedro Cardoso"))
                .andExpect(jsonPath("$.completedActivitiesToday").value(1))
                .andExpect(jsonPath("$.weeklyActivityCount").value(1))
                .andExpect(jsonPath("$.goalCompletionPercent").value(25))
                .andExpect(jsonPath("$.recentActivities[0].type").value("SPEAKING"))
                .andExpect(jsonPath("$.nextSuggestedAction.route").value("/write"))
                .andReturn();

        MvcResult progressResult = mockMvc.perform(get("/api/progress/summary")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(
                objectMapper.readTree(progressResult.getResponse().getContentAsString()).get("streakDays").asInt(),
                objectMapper.readTree(dashboardResult.getResponse().getContentAsString()).get("currentStreakDays").asInt()
        );
    }

    @Test
    void shouldRejectUnauthenticatedDashboard() throws Exception {
        mockMvc.perform(get("/api/dashboard/summary"))
                .andExpect(status().isUnauthorized());
    }

    private String registerAndAuthenticate() throws Exception {
        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "email": "pedro@example.com",
                                  "password": "StrongPassword123",
                                  "targetLanguage": "ENGLISH"
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
