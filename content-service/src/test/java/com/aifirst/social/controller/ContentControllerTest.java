package com.aifirst.social.controller;

import com.aifirst.social.model.Content;
import com.aifirst.social.config.SecurityConfig;
import com.aifirst.social.model.Content;
import com.aifirst.social.model.ContentType;
import com.aifirst.social.repository.ContentRepository;
import com.aifirst.social.service.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
@Import(SecurityConfig.class)
public class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentRepository contentRepository;

    @MockBean
    private StorageService storageService;

    @Test
    @WithMockUser
    public void testUploadContent() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        when(storageService.store(any())).thenReturn("some-url");

        mockMvc.perform(multipart("/content/upload")
                        .file(file)
                        .param("caption", "A great caption")
                        .param("type", "TEXT")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetContent() throws Exception {
        Content content = new Content();
        content.setId(1L);
        content.setUserId(1L);
        content.setCaption("A great caption");
        content.setType(ContentType.TEXT);

        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        mockMvc.perform(get("/content/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.caption").value("A great caption"));
    }
}
