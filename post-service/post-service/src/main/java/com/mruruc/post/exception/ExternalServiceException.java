package com.mruruc.post.exception;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
