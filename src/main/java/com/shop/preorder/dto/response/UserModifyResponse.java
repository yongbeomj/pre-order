package com.shop.preorder.dto.response;

import com.shop.preorder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyResponse {

    private Long id;
    private String email;
    private String name;
    private String profileImage;
    private String greeting;

    // entity to dto
    public static UserModifyResponse of(User user) {
        return new UserModifyResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getProfileImage(),
                user.getGreeting()
        );
    }

}
