package com.shop.ecommerceservice.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private boolean success;
    private T response;
    private ErrorResponse error;

    @Builder
    public ResponseDto(boolean success, T response, ErrorResponse error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }

    public static <T> ResponseDto<T> success(T response) {
        return ResponseDto.<T>builder()
                .success(true)
                .response(response)
                .build();
    }

    public static <T> ResponseDto<T> error(ErrorResponse response) {
        return ResponseDto.<T>builder()
                .success(false)
                .error(response)
                .build();
    }

}
