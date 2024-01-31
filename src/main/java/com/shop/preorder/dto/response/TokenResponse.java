package com.shop.preorder.dto.response;

import com.shop.preorder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private String email;

    public static TokenResponse of(String email) {
        return new TokenResponse(email);
    }

}
