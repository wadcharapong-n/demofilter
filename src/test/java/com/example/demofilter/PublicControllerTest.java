package com.example.demofilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
class PublicControllerTest {

    @MockBean
    private JwtService jwtService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        when(jwtService.createJwt(any(UserProfile.class), any(Long.class))).thenReturn("mockAdminToken");
    }

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    void testGetTokenAdmin() throws Exception {
        mockMvc.perform(get("/public/token/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("mockAdminToken"));
    }

    @Test
    void getTokenUser() throws Exception {
        mockMvc.perform(get("/public/token/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("mockAdminToken"));
    }

    @Test
    void getTokenBoth() throws Exception {
        mockMvc.perform(get("/public/token/both"))
                .andExpect(status().isOk())
                .andExpect(content().string("mockAdminToken"));
    }
}