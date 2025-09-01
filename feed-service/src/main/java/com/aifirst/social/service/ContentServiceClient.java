package com.aifirst.social.service;

import com.aifirst.social.dto.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    public List<Post> getAllContent(String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Post>> response = restTemplate.exchange(
                contentServiceUrl + "/content",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Post>>() {}
        );
        return response.getBody();
    }
}
