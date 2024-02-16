package com.shop.paymentservice.common.exception;

import com.shop.paymentservice.common.response.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

}
