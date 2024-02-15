package com.shop.productservice.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private int statusCode;
    private String message;

    @Builder
    public ErrorResponse(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .status(errorCode.getHttpStatus())
                .message(errorCode.getMessage())
                .build();
    }

}
