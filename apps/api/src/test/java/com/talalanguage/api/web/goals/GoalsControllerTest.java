package com.talalanguage.api.web.goals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.goals.InMemoryGoalSettingsRepository;
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
class GoalsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryGoalSettingsRepository goalSettingsRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        goalSettingsRepository.clear();
    }

    @Test
    void shouldReturnDefaultGoalsForAuthenticatedUser() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(get("/api/goals")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dailyMinutes").value(25))
                .andExpect(jsonPath("$.weeklyMinutes").value(180))
                .andExpect(jsonPath("$.wordsPerDay").value(20))
                .andExpect(jsonPath("$.challengesPerWeek").value(4));
    }

    @Test
    void shouldUpdateGoals() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(put("/api/goals")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "dailyMinutes": 30,
                                  "weeklyMinutes": 210,
                                  "wordsPerDay": 25,
                                  "challengesPerWeek": 5
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dailyMinutes").value(30))
                .andExpect(jsonPath("$.weeklyMinutes").value(210))
                .andExpect(jsonPath("$.wordsPerDay").value(25))
                .andExpect(jsonPath("$.challengesPerWeek").value(5));
    }

    @Test
    void shouldRejectUnauthenticatedGoalsRequests() throws Exception {
        mockMvc.perform(get("/api/goals"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/api/goals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "dailyMinutes": 30,
                                  "weeklyMinutes": 210,
                                  "wordsPerDay": 25,
                                  "challengesPerWeek": 5
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectInvalidGoalValues() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(put("/api/goals")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "dailyMinutes": 0,
                                  "weeklyMinutes": 210,
                                  "wordsPerDay": 25,
                                  "challengesPerWeek": 5
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("VALIDATION_ERROR"));
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
}
