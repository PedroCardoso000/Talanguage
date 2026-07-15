package com.talalanguage.api.web.writing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.writing.InMemoryWritingSubmissionRepository;
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
class WritingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryWritingSubmissionRepository writingSubmissionRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        writingSubmissionRepository.clear();
    }

    @Test
    void shouldListChallenges() throws Exception {
        mockMvc.perform(get("/api/writing/challenges"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("follow-up-email"))
                .andExpect(jsonPath("$[0].focus").value("Professional tone and clarity."));
    }

    @Test
    void shouldReturnCurrentChallenge() throws Exception {
        mockMvc.perform(get("/api/writing/challenges/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("follow-up-email"));
    }

    @Test
    void shouldCreateReviewedSubmission() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/writing/submissions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "challengeId": "follow-up-email",
                                  "content": "My name is Pedro. I am from Brazil. I work with software. I want to learn English."
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("REVIEWED"))
                .andExpect(jsonPath("$.feedback.strengths[0]").isNotEmpty())
                .andExpect(jsonPath("$.feedback.nextAction").isNotEmpty());
    }

    @Test
    void shouldRejectEmptySubmission() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/writing/submissions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "challengeId": "follow-up-email",
                                  "content": ""
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRequireAuthForSubmissionsAndRecent() throws Exception {
        mockMvc.perform(post("/api/writing/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "challengeId": "follow-up-email",
                                  "content": "Some content"
                                }
                                """))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/writing/submissions/recent"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnNotFoundForUnknownChallenge() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/writing/submissions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "challengeId": "missing-challenge",
                                  "content": "Some content"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value("NOT_FOUND"));
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
