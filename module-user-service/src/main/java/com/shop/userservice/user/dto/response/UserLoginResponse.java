package com.shop.userservice.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {

    private String token;

    public static UserLoginResponse of(String token) {
        return new UserLoginResponse(token);
    }

}
