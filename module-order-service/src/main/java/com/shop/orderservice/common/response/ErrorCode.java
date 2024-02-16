package com.shop.orderservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    INVALID_AUTH_CODE(HttpStatus.UNAUTHORIZED, "Invalid email authentication code"),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not founded"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment not founded"),

    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "Duplicated user email"),
    DUPLICATED_POST_LIKE(HttpStatus.CONFLICT, "User already like the post"),
    DUPLICATED_COMMENT_LIKE(HttpStatus.CONFLICT, "User already like the comment"),

    ALREADY_USER_FOLLOW(HttpStatus.CONFLICT, "Already user followed"),
    NOT_FOLLOW_ME(HttpStatus.BAD_REQUEST, "Users can't follow themselves"),

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not founded"),
    DUPLICATED_PRODUCT_TITLE(HttpStatus.CONFLICT, "Duplicated product title"),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (Database Error)"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (System Error)");

    private final HttpStatus httpStatus;
    private final String message;
}
