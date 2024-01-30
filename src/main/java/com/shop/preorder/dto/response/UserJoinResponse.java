package com.shop.preorder.dto.response;

import com.shop.preorder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
