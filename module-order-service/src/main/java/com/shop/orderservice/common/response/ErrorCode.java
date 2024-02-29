package com.shop.orderservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),

    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Order infomation not found"),
    ORDER_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "Not an available time to order"),

    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "The product is out of stock"),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (Database Error)"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (System Error)");

    private final HttpStatus httpStatus;
    private final String message;
}
