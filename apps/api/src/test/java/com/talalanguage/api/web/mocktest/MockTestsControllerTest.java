package com.talalanguage.api.web.mocktest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.mocktest.InMemoryMockTestAttemptRepository;
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
class MockTestsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryMockTestAttemptRepository mockTestAttemptRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        mockTestAttemptRepository.clear();
    }

    @Test
    void shouldReturnCurrentMockTest() throws Exception {
        mockMvc.perform(get("/api/mock-tests/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("baseline-english-v1"))
                .andExpect(jsonPath("$.questions[0].id").value("q1"));
    }

    @Test
    void shouldSubmitAttemptAndFetchIt() throws Exception {
        String token = registerAndAuthenticate();

        MvcResult submitResult = mockMvc.perform(post("/api/mock-tests/attempts")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "mockTestId": "baseline-english-v1",
                                  "answers": [
                                    { "questionId": "q1", "selectedOption": "She goes to work every day." },
                                    { "questionId": "q2", "selectedOption": "Let me walk you through the main points." },
                                    { "questionId": "q3", "selectedOption": "finish" },
                                    { "questionId": "q4", "selectedOption": "I totally agree with you." },
                                    { "questionId": "q5", "selectedOption": "However" }
                                  ]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.score").value(5))
                .andReturn();

        String attemptId = objectMapper.readTree(submitResult.getResponse().getContentAsString())
                .get("attemptId")
                .asText();

        mockMvc.perform(get("/api/mock-tests/attempts/" + attemptId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attemptId").value(attemptId))
                .andExpect(jsonPath("$.questions.length()").value(5));
    }

    @Test
    void shouldRejectIncompleteAttempt() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/mock-tests/attempts")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "mockTestId": "baseline-english-v1",
                                  "answers": [
                                    { "questionId": "q1", "selectedOption": "She goes to work every day." }
                                  ]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("VALIDATION_ERROR"));
    }

    @Test
    void shouldRejectUnauthenticatedAttemptSubmission() throws Exception {
        mockMvc.perform(post("/api/mock-tests/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "mockTestId": "baseline-english-v1",
                                  "answers": [
                                    { "questionId": "q1", "selectedOption": "She goes to work every day." }
                                  ]
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    private String registerAndAuthenticate() throws Exception {
        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "email": "pedro-mocktest@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode payload = objectMapper.readTree(registerResult.getResponse().getContentAsString());
        return payload.get("accessToken").asText();
    }
}
