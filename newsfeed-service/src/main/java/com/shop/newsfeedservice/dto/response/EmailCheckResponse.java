package com.shop.newsfeedservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailCheckResponse {

    private int authCode;

    public static EmailCheckResponse of(int authCode) {
        return new EmailCheckResponse(authCode);
    }

}
