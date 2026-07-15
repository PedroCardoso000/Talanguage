package com.talalanguage.api.web.community;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talalanguage.api.infrastructure.auth.InMemorySessionService;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.community.InMemoryCommunityInterestRepository;
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
class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InMemoryUserRepository userRepository;

    @Autowired
    private InMemorySessionService sessionService;

    @Autowired
    private InMemoryCommunityInterestRepository communityInterestRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
        sessionService.clear();
        communityInterestRepository.clear();
    }

    @Test
    void shouldRequireAuth() throws Exception {
        mockMvc.perform(get("/api/community/groups"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/community/partners"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/api/community/interests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "targetType": "GROUP",
                                  "targetId": "group-travel-beginners"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldListCommunityResourcesAndRegisterInterest() throws Exception {
        String token = registerAndAuthenticate();

        mockMvc.perform(get("/api/community/groups")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("language", "ENGLISH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("group-travel-beginners"));

        mockMvc.perform(get("/api/community/partners")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("language", "SPANISH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("partner-carlos"));

        mockMvc.perform(post("/api/community/interests")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "targetType": "GROUP",
                                  "targetId": "group-travel-beginners"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.targetType").value("GROUP"));

        mockMvc.perform(post("/api/community/interests")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "targetType": "GROUP",
                                  "targetId": "group-travel-beginners"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.targetId").value("group-travel-beginners"));
    }

    private String registerAndAuthenticate() throws Exception {
        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pedro Cardoso",
                                  "email": "pedro-community@example.com",
                                  "password": "StrongPassword123"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode payload = objectMapper.readTree(registerResult.getResponse().getContentAsString());
        return payload.get("accessToken").asText();
    }
}
