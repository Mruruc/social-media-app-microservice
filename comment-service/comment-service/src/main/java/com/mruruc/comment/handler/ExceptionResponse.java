package com.mruruc.comment.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExceptionResponse {
    private String message;
}
