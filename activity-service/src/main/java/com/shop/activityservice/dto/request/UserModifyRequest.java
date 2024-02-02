package com.shop.activityservice.dto.request;

import com.shop.activityservice.domain.User;
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
