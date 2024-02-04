package com.shop.activityservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "comment_likes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @Builder
    public CommentLike(Long userId, Comment comment) {
        this.userId = userId;
        this.comment = comment;
    }
}
