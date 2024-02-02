package com.shop.userservice.dto.request;

import com.shop.userservice.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequest {

    @NotBlank
    @Pattern(regexp = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$")
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String profileImage;

    @NotBlank
    private String greeting;

    private int AutoNumber;

    // dto to entity
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .profileImage(this.profileImage)
                .greeting(this.greeting)
                .build();
    }

}
