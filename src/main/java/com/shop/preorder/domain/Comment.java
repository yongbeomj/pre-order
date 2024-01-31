package com.shop.preorder.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "comments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Comment(String contents, Post post, User user) {
        this.contents = contents;
        this.post = post;
        this.user = user;
    }
}
