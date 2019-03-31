package com.example.films.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Test
    @DisplayName("login with valid user is authenticated")
    void loginWithValidUser() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user(username)
                .password(password);

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername(username));
    }

    @Test
    @DisplayName("login with invalid user is unauthenticated")
    void loginWithInValidUser() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("invalidUser")
                .password("invalidPassword");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("access secured resource unauthenticated redirects to login")
    void accessSecuredResourceUnauthenticated_redirectsToLogin() throws Exception {
        mockMvc.perform(get("/films"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    @DisplayName("access secured resource authenticated is ok")
    void accessSecuredResourceAuthenticatd_isOk() throws Exception {
        mockMvc.perform(get("/films"))
                .andExpect(status().isOk());
    }
}
