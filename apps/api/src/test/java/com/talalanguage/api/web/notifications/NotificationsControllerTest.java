package com.talalanguage.api.web.notifications;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.notifications.InMemoryNotificationRepository;
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
class NotificationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryNotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        notificationRepository.clear();
    }

    @Test
    void shouldListNotificationsForAuthenticatedUser() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(get("/api/notifications")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Bem-vindo ao Tala"))
                .andExpect(jsonPath("$[0].read").value(false));
    }

    @Test
    void shouldMarkNotificationAsRead() throws Exception {
        String token = registerAndAuthenticate();
        MvcResult listResult = mockMvc.perform(get("/api/notifications")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String notificationId = objectMapper.readTree(listResult.getResponse().getContentAsString()).get(0).get("id").asText();

        mockMvc.perform(patch("/api/notifications/" + notificationId + "/read")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.read").value(true))
                .andExpect(jsonPath("$.readAt").isNotEmpty());
    }

    @Test
    void shouldRejectUnauthenticatedNotificationsRequests() throws Exception {
        mockMvc.perform(get("/api/notifications"))
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
}
