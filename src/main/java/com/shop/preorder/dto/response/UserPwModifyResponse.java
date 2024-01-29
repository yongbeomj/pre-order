package com.shop.preorder.dto.response;

import com.shop.preorder.domain.User;
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
