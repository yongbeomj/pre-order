package com.shop.newsfeedservice.dto.response;

import com.shop.newsfeedservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinResponse {

    private Long id;
    private String email;

    // entity to dto
    public static UserJoinResponse of(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getEmail()
        );
    }


}
