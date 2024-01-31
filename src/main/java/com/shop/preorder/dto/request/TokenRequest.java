package com.shop.preorder.dto.request;

import com.shop.preorder.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    private String token;
    private boolean expired;

    // dto to entity
    public Token toEntity() {
        return Token.builder()
                .token(this.token)
                .expired(this.expired)
                .build();
    }

}
