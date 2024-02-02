package com.shop.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private String email;

    public static TokenResponse of(String email) {
        return new TokenResponse(email);
    }

}
