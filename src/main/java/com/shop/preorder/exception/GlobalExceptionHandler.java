package com.shop.preorder.exception;

import com.shop.preorder.dto.common.ErrorResponse;
import com.shop.preorder.dto.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> errorHandler(BaseException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ResponseDto.error(ErrorResponse.of(e.getErrorCode())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> databaseErrorHandler(IllegalArgumentException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getHttpStatus())
                .body(ResponseDto.error(ErrorResponse.of(ErrorCode.DATABASE_ERROR)));
    }

}
