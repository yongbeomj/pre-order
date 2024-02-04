package com.shop.activityservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "post_likes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Builder
    public PostLike(Long userId, Post post) {
        this.userId = userId;
        this.post = post;
    }
}
