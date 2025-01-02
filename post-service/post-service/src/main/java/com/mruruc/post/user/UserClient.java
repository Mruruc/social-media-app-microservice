package com.mruruc.post.user;

import com.mruruc.post.exception.ExternalServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserClient {
    @Value("${application.config.client.uri.user-service}")
    private String USER_SERVICE;
    private final RestTemplate restTemplate;


    public boolean isUserExist(Long userId) {
        URI uri = UriComponentsBuilder
                .fromUriString("{USER_SERVICE}/exist/{userId}")
                .buildAndExpand(USER_SERVICE, userId)
                .toUri();
        try {
            return Boolean.TRUE.equals(
                    restTemplate.getForObject(uri, Boolean.class));
        } catch (RestClientException exception) {
            throw new ExternalServiceException(
                    "Error checking user existence for ID: " + userId, exception
            );
        }
    }

}
