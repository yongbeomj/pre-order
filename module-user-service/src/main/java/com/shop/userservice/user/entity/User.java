package com.shop.userservice.user.entity;

import com.shop.userservice.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String greeting;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Builder
    public User(String email, String password, String name, String profileImage, String greeting, UserRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.greeting = greeting;
        this.role = role;
    }
}
