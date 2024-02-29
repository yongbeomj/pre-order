package com.shop.userservice.user.entity;

import com.shop.userservice.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "jwt_blacklist")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String email;
    private boolean expired;

    @Builder
    public Token(String token, String email, boolean expired) {
        this.token = token;
        this.email = email;
        this.expired = expired;
    }
}
