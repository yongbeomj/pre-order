package com.shop.paymentservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),

    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Payment information not founded"),
    FAIL_TO_ACQUIRE_LOCK(HttpStatus.BAD_REQUEST, "Failed to acquire a lock"),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (Database Error)"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (System Error)");

    private final HttpStatus httpStatus;
    private final String message;
}
