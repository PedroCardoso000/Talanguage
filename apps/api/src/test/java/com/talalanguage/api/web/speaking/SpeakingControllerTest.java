package com.talalanguage.api.web.speaking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingSessionRepository;
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
class SpeakingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemorySpeakingSessionRepository speakingSessionRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        speakingSessionRepository.clear();
    }

    @Test
    void shouldRequireAuthForSpeakingEndpoints() throws Exception {
        mockMvc.perform(get("/api/speaking/topics"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/api/speaking/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "language": "ENGLISH",
                                  "level": "BEGINNER",
                                  "topicId": "travel"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRunSpeakingFlow() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(get("/api/speaking/topics")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("presentation"))
                .andExpect(jsonPath("$[0].mentorMessage").isString());

        MvcResult startResult = mockMvc.perform(post("/api/speaking/sessions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "language": "ENGLISH",
                                  "level": "BEGINNER",
                                  "topicId": "travel"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andReturn();

        String sessionId = objectMapper.readTree(startResult.getResponse().getContentAsString())
                .get("sessionId")
                .asText();

        mockMvc.perform(post("/api/speaking/sessions/{sessionId}/responses", sessionId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "content": "I would like to confirm my reservation and ask about breakfast."
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPrompt").isString());

        mockMvc.perform(post("/api/speaking/sessions/{sessionId}/finish", sessionId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("FINISHED"))
                .andExpect(jsonPath("$.summary.totalMessages").value(1))
                .andExpect(jsonPath("$.summary.topicTitle").value("Check-in no hotel"))
                .andExpect(jsonPath("$.summary.nextAction").isString());

        mockMvc.perform(get("/api/speaking/sessions/recent")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sessionId").value(sessionId))
                .andExpect(jsonPath("$[0].totalMessages").value(1));
    }

    @Test
    void shouldRejectUnknownTopicAndEmptyResponse() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/speaking/sessions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "language": "ENGLISH",
                                  "level": "BEGINNER",
                                  "topicId": "missing-topic"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value("NOT_FOUND"));

        MvcResult startResult = mockMvc.perform(post("/api/speaking/sessions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "language": "ENGLISH",
                                  "level": "BEGINNER",
                                  "topicId": "travel"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        String sessionId = objectMapper.readTree(startResult.getResponse().getContentAsString())
                .get("sessionId")
                .asText();

        mockMvc.perform(post("/api/speaking/sessions/{sessionId}/responses", sessionId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "content": ""
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
                                  "email": "pedro-speaking@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode payload = objectMapper.readTree(registerResult.getResponse().getContentAsString());
        return payload.get("accessToken").asText();
    }
}
