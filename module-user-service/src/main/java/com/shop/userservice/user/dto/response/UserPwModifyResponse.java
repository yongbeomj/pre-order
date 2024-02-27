package com.shop.userservice.user.dto.response;

import com.shop.userservice.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPwModifyResponse {

    private Long id;
    private String email;

    // entity to dto
    public static UserPwModifyResponse of(User user) {
        return new UserPwModifyResponse(
                user.getId(),
                user.getEmail()
        );
    }

}
