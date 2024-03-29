package com.shop.activityservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not founded"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment not founded"),

    DUPLICATED_POST_LIKE(HttpStatus.CONFLICT, "User already like the post"),
    DUPLICATED_COMMENT_LIKE(HttpStatus.CONFLICT, "User already like the comment"),

    ALREADY_USER_FOLLOW(HttpStatus.CONFLICT, "Already user followed"),
    NOT_FOLLOW_ME(HttpStatus.BAD_REQUEST, "Users can't follow themselves"),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (Database Error)"),
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "개발팀에 문의해주세요. (System Error)");

    private final HttpStatus httpStatus;
    private final String message;
}
