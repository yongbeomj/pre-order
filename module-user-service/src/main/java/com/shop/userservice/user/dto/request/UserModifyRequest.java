package com.shop.userservice.user.dto.request;

import com.shop.userservice.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyRequest {

    private String name;
    private String profileImage;
    private String greeting;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .profileImage(this.profileImage)
                .greeting(this.greeting)
                .build();
    }

}
