package com.mruruc.comment.user;

import com.mruruc.comment.exception.ExternalServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserClient {
    @Value("${application.config.client.uri.user-service}")
    private String userServiceUri;
    private final RestClient restClient;


    public boolean isUserExist(Long userId) {
        try {
            return Boolean.TRUE.equals(
                    restClient.get()
                            .uri(format("%s/exist/%s", userServiceUri, userId))
                            .retrieve()
                            .body(Boolean.class)
            );
        } catch (Exception ex) {
            throw new ExternalServiceException("Error checking user existence for ID: " + userId, ex);
        }
    }

}
