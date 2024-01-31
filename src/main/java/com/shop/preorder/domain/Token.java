package com.shop.preorder.domain;

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
    private boolean expired;

    @Builder
    public Token(String token, boolean expired) {
        this.token = token;
        this.expired = expired;
    }
}
