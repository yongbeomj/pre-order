package com.shop.preorder.util;

import com.shop.preorder.dto.common.ErrorResponse;
import com.shop.preorder.dto.common.ResponseDto;

public class ResponseUtil {

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
