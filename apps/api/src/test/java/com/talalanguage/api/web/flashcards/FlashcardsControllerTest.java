package com.talalanguage.api.web.flashcards;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardRepository;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardStatsRepository;
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
class FlashcardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryFlashcardRepository flashcardRepository;

    @Autowired
    private InMemoryFlashcardStatsRepository flashcardStatsRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        flashcardRepository.clear();
        flashcardStatsRepository.clear();
    }

    @Test
    void shouldListFlashcards() throws Exception {
        String token = registerAndAuthenticate();
        createFlashcard(token, "How are you?", "Como voce esta?");

        mockMvc.perform(get("/api/flashcards")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty());
    }

    @Test
    void shouldCreateFlashcard() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/flashcards")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "front": "How are you?",
                                  "back": "Como voce esta?",
                                  "language": "ENGLISH",
                                  "tags": ["greetings"]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.front").value("How are you?"));
    }

    @Test
    void shouldReviewFlashcardAndReturnStats() throws Exception {
        String token = registerAndAuthenticate();
        String flashcardId = createFlashcard(token, "How are you?", "Como voce esta?");

        mockMvc.perform(post("/api/flashcards/" + flashcardId + "/review")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                { "rating": "GOOD" }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewCount").value(1));

        mockMvc.perform(get("/api/flashcards/stats")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewedCount").value(1))
                .andExpect(jsonPath("$.correctCount").value(1))
                .andExpect(jsonPath("$.overallProgress").value(100));
    }

    @Test
    void shouldDeleteFlashcard() throws Exception {
        String token = registerAndAuthenticate();
        String flashcardId = createFlashcard(token, "Deadline", "Prazo final");

        mockMvc.perform(delete("/api/flashcards/" + flashcardId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/flashcards")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldRejectUnauthenticatedRequests() throws Exception {
        mockMvc.perform(get("/api/flashcards"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/api/flashcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "front": "How are you?",
                                  "back": "Como voce esta?",
                                  "language": "ENGLISH",
                                  "tags": ["greetings"]
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectInvalidFlashcardPayload() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(post("/api/flashcards")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "front": "",
                                  "back": "Como voce esta?",
                                  "language": "ENGLISH",
                                  "tags": []
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

    private String createFlashcard(String token, String front, String back) throws Exception {
        MvcResult createResult = mockMvc.perform(post("/api/flashcards")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "front": "%s",
                                  "back": "%s",
                                  "language": "ENGLISH",
                                  "tags": ["greetings"]
                                }
                                """.formatted(front, back)))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(createResult.getResponse().getContentAsString()).get("id").asText();
    }
}
