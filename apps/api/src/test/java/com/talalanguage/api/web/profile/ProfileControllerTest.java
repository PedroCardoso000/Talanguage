package com.talalanguage.api.web.profile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
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
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
    }

    @Test
    void shouldReturnAuthenticatedProfile() throws Exception {
        String accessToken = registerAndExtractToken();

        mockMvc.perform(get("/api/profile/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("pedro@example.com"))
                .andExpect(jsonPath("$.targetLanguage").value("ENGLISH"));
    }

    @Test
    void shouldUpdateAuthenticatedProfile() throws Exception {
        String accessToken = registerAndExtractToken();

        mockMvc.perform(put("/api/profile/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro C.",
                                  "targetLanguage": "FRENCH",
                                  "currentLevel": "INTERMEDIATE",
                                  "studyGoal": "Quero conversar por 20 minutos sem travar.",
                                  "avatarUrl": "https://images.example.com/pedro.png"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pedro C."))
                .andExpect(jsonPath("$.targetLanguage").value("FRENCH"))
                .andExpect(jsonPath("$.currentLevel").value("INTERMEDIATE"))
                .andExpect(jsonPath("$.studyGoal").value("Quero conversar por 20 minutos sem travar."))
                .andExpect(jsonPath("$.avatarUrl").value("https://images.example.com/pedro.png"));

        mockMvc.perform(get("/api/auth/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetLanguage").value("FRENCH"))
                .andExpect(jsonPath("$.currentLevel").value("INTERMEDIATE"));
    }

    @Test
    void shouldRejectInvalidAvatarUrl() throws Exception {
        String accessToken = registerAndExtractToken();

        mockMvc.perform(put("/api/profile/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "targetLanguage": "ENGLISH",
                                  "currentLevel": "BEGINNER",
                                  "studyGoal": "Meta valida.",
                                  "avatarUrl": "ftp://avatar.png"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("VALIDATION_ERROR"));
    }

    private String registerAndExtractToken() throws Exception {
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
}
