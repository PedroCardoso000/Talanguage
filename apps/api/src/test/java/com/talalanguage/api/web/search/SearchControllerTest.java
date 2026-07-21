package com.talalanguage.api.web.search;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardRepository;
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
class SearchControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private InMemoryUserRepository userRepository;
    @Autowired private InMemorySessionService sessionService;
    @Autowired private InMemoryFlashcardRepository flashcardRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        flashcardRepository.clear();
    }

    @Test
    void shouldRequireAuthenticationAndValidateInput() throws Exception {
        mockMvc.perform(get("/api/search").param("q", "travel"))
                .andExpect(status().isUnauthorized());

        String token = register("owner@example.com");
        mockMvc.perform(get("/api/search").header(HttpHeaders.AUTHORIZATION, bearer(token)).param("q", "a"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/search").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .param("q", "travel").param("types", "UNKNOWN"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/search").header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .param("q", "travel").param("limit", "31"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldSearchCaseInsensitivelyAndFilterByType() throws Exception {
        String token = register("owner@example.com");
        createFlashcard(token, "Travel", "Airport vocabulary");

        mockMvc.perform(get("/api/search")
                        .header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .param("q", "  tRaVeL  ")
                        .param("types", "FLASHCARD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.query").value("tRaVeL"))
                .andExpect(jsonPath("$.results.length()").value(1))
                .andExpect(jsonPath("$.results[0].type").value("FLASHCARD"))
                .andExpect(jsonPath("$.results[0].title").value("Travel"))
                .andExpect(jsonPath("$.results[0].route").value("/review"))
                .andExpect(jsonPath("$.results[0].score").value(1.0))
                .andExpect(jsonPath("$.results[0].userId").doesNotExist())
                .andExpect(jsonPath("$.results[0].email").doesNotExist())
                .andExpect(jsonPath("$.results[0].token").doesNotExist());
    }

    @Test
    void shouldReturnOnlyAuthenticatedUsersPrivateResources() throws Exception {
        String ownerToken = register("owner@example.com");
        createFlashcard(ownerToken, "Private travel card", "Only the owner sees this");
        String otherToken = register("other@example.com");

        mockMvc.perform(get("/api/search")
                        .header(HttpHeaders.AUTHORIZATION, bearer(otherToken))
                        .param("q", "private")
                        .param("types", "FLASHCARD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results").isEmpty());
    }

    @Test
    void shouldRespectLimitAndRankExactBeforePartial() throws Exception {
        String token = register("owner@example.com");
        createFlashcard(token, "My travel notes", "Partial match");
        createFlashcard(token, "Travel", "Exact match");
        createFlashcard(token, "Travel vocabulary", "Prefix match");

        mockMvc.perform(get("/api/search")
                        .header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .param("q", "travel")
                        .param("types", "FLASHCARD")
                        .param("limit", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(2))
                .andExpect(jsonPath("$.results[0].title").value("Travel"))
                .andExpect(jsonPath("$.results[1].title").value("Travel vocabulary"));
    }

    private String register(String email) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Search User","email":"%s","password":"StrongPassword123"}
                                """.formatted(email)))
                .andExpect(status().isCreated())
                .andReturn();
        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return json.get("accessToken").asText();
    }

    private void createFlashcard(String token, String front, String back) throws Exception {
        mockMvc.perform(post("/api/flashcards")
                        .header(HttpHeaders.AUTHORIZATION, bearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"front":"%s","back":"%s","language":"ENGLISH","tags":[]}
                                """.formatted(front, back)))
                .andExpect(status().isCreated());
    }

    private String bearer(String token) {
        return "Bearer " + token;
    }
}
