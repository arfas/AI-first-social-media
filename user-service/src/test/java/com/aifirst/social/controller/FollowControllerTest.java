package com.aifirst.social.controller;

import com.aifirst.social.config.SecurityConfig;
import com.aifirst.social.model.Follow;
import com.aifirst.social.repository.FollowRepository;
import com.aifirst.social.secuirty.JwtTokenProvider;
import com.aifirst.social.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FollowController.class)
@Import(SecurityConfig.class)
public class FollowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FollowRepository followRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @WithMockUser
    public void testFollowUser() throws Exception {
        mockMvc.perform(post("/follow/2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetFollowing() throws Exception {
        Follow follow = new Follow(1L, 2L);
        when(followRepository.findByFollowerId(1L)).thenReturn(Collections.singletonList(follow));

        mockMvc.perform(get("/follow/following/1"))
                .andExpect(status().isOk());
    }
}
