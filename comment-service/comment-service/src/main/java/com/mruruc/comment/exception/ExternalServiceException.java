package com.mruruc.comment.exception;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message, Exception exception) {
        super(message, exception);
    }

    public ExternalServiceException(String message) {
        super(message);
    }
}
