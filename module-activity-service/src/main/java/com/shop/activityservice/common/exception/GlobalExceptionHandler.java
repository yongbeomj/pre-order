package com.shop.activityservice.common.exception;

import com.shop.activityservice.common.response.ErrorResponse;
import com.shop.activityservice.common.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> globalException(BaseException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ResponseDto.error(ErrorResponse.of(e.getErrorCode())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> globalException(IllegalArgumentException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getHttpStatus())
                .body(ResponseDto.error(ErrorResponse.of(ErrorCode.DATABASE_ERROR)));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> globalException(RuntimeException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(ErrorCode.SYSTEM_ERROR.getHttpStatus())
                .body(ResponseDto.error(ErrorResponse.of(ErrorCode.SYSTEM_ERROR)));
    }

}
