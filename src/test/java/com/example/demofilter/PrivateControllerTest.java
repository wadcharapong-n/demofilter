package com.example.demofilter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrivateController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
class PrivateControllerTest {

    @MockBean
    private JwtService jwtService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void helloUser() throws Exception {
        mockMvc.perform(get("/private/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World private !!!!!"));
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ADMIN"})
    void helloUser_by_another_roles() throws Exception {
        mockMvc.perform(get("/private/user"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void helloAdmin() throws Exception {
        mockMvc.perform(get("/private/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World private admin !!!!!"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"USER"})
    void helloAdmin_by_another_roles() throws Exception {
        mockMvc.perform(get("/private/admin"))
                .andExpect(status().isForbidden());
    }
}