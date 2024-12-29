package com.mruruc.comment.handler;

import com.mruruc.comment.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }

//    @ExceptionHandler(AuthorizationException.class)
//    public ResponseEntity<ExceptionResponse> handleAuthorizationExceptionException(AuthorizationException exception) {
//        return ResponseEntity
//                .status(UNAUTHORIZED)
//                .body(ExceptionResponse.builder()
//                        .message(exception.getMessage())
//                        .build());
//    }
}

