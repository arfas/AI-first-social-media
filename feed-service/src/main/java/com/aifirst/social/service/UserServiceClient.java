package com.aifirst.social.service;

import com.aifirst.social.dto.Follow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserServiceClient(RestTemplate restTemplate, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    public List<Follow> getFollowing(Long userId) {
        ResponseEntity<List<Follow>> response = restTemplate.exchange(
                userServiceUrl + "/follow/following/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Follow>>() {}
        );
        return response.getBody();
    }
}
