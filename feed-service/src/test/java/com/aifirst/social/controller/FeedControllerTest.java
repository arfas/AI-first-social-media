package com.aifirst.social.controller;

import com.aifirst.social.dto.Post;
import com.aifirst.social.service.ContentServiceClient;
import com.aifirst.social.service.FeedRanker;
import com.aifirst.social.service.UserServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedController.class)
public class FeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentServiceClient contentServiceClient;

    @MockBean
    private UserServiceClient userServiceClient;

    @MockBean
    private FeedRanker feedRanker;

    @Test
    public void testGetFeed() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setUserId(1L);
        post.setCaption("A great post");

        when(contentServiceClient.getAllContent()).thenReturn(Collections.singletonList(post));
        when(userServiceClient.getFollowing(any())).thenReturn(Collections.emptyList());
        when(feedRanker.rank(any(), any())).thenReturn(Collections.singletonList(post));

        mockMvc.perform(get("/feed/1"))
                .andExpect(status().isOk());
    }
}
