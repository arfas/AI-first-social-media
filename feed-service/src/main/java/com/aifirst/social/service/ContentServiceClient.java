package com.aifirst.social.service;

import com.aifirst.social.dto.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContentServiceClient {

    private final RestTemplate restTemplate;
    private final String contentServiceUrl;

    public ContentServiceClient(RestTemplate restTemplate, @Value("${content.service.url}") String contentServiceUrl) {
        this.restTemplate = restTemplate;
        this.contentServiceUrl = contentServiceUrl;
    }

    public List<Post> getAllContent() {
        // In a real app, you would have an endpoint in content-service to get all content
        // For now, we will assume such an endpoint exists at /content
        // and it returns a list of Post objects.
        // We also need to add an endpoint to content-service to fetch all content
        ResponseEntity<List<Post>> response = restTemplate.exchange(
                contentServiceUrl + "/content",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Post>>() {}
        );
        return response.getBody();
    }
}
