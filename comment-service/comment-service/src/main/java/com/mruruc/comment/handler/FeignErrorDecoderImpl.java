package com.mruruc.comment.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mruruc.comment.exception.ExternalServiceException;
import com.mruruc.comment.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FeignErrorDecoderImpl implements ErrorDecoder {
    private final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionResponse exceptionResponse = null;
        try {
            exceptionResponse =
                    MAPPER.readValue(response.body().asInputStream(), ExceptionResponse.class);
        } catch (IOException exception) {
            System.out.println(response);
            log.error("Error decoding Feign response", exception);
        }

        int status = response.status();
        return handleExceptionBasedOnStatus(status, exceptionResponse);
    }

    private Exception handleExceptionBasedOnStatus(int status, ExceptionResponse exceptionResponse) {
        switch (status) {
            case 404 -> throw new ResourceNotFoundException(exceptionResponse.getMessage());
            default -> throw new ExternalServiceException(exceptionResponse.getMessage());
        }
    }
}
