package com.aifirst.social.controller;

import com.aifirst.social.config.SecurityConfig;
import com.aifirst.social.model.Comment;
import com.aifirst.social.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void testAddComment() throws Exception {
        mockMvc.perform(post("/comment/1")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("A great comment")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetComments() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContentId(1L);
        comment.setUserId(1L);
        comment.setText("A great comment");

        when(commentRepository.findByContentId(1L)).thenReturn(Collections.singletonList(comment));

        mockMvc.perform(get("/comment/1"))
                .andExpect(status().isOk());
    }
}
