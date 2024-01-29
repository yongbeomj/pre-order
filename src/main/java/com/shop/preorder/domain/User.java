package com.shop.preorder.domain;

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
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String greeting;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Builder
    public User(String email, String password, String name, String profileImage, String greeting) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.greeting = greeting;
    }

}
