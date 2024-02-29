package com.shop.stockservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),

    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "The product is out of stock"),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (Database Error)"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (System Error)");

    private final HttpStatus httpStatus;
    private final String message;
}
