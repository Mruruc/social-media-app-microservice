package com.mruruc.comment.post;

import com.mruruc.comment.exception.ExternalServiceException;
import com.mruruc.comment.exception.ResourceNotFoundException;
import com.mruruc.comment.handler.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostClient {
    @Value("${application.config.client.uri.post-service}")
    private String POST_SERVICE_URI;
    private final RestClient restClient;


    public void verifyPostExists(Long postId) {
        restClient.get()
                .uri(format("%s/exist/%d", POST_SERVICE_URI, postId))
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    var statusCode = response.getStatusCode();
                    if (statusCode.is2xxSuccessful()) {
                        return statusCode;
                    }
                    var exceptionResponse =
                            response.bodyTo(ExceptionResponse.class);
                    if (statusCode.equals(NOT_FOUND)) {
                        throw new ResourceNotFoundException(exceptionResponse.getMessage());
                    }
                    throw new ExternalServiceException(
                            format("Unexpected status %s from Post Service: %s", statusCode, exceptionResponse.getMessage()
                            ));
                });

    }

}
