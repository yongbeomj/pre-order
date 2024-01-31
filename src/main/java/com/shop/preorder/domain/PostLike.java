package com.shop.preorder.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Builder
    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
