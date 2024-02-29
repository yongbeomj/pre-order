package com.shop.activityservice.comment.entiity;

import com.shop.activityservice.common.entity.BaseTimeEntity;
import com.shop.activityservice.client.dto.ActivityType;
import com.shop.activityservice.client.dto.NewsfeedCreateRequest;
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

    public NewsfeedCreateRequest newsfeedCreateRequest(Long activityUserId) {
        return NewsfeedCreateRequest.builder()
                .activityUserId(activityUserId)
                .targetUserId(userId)
                .targetId(id)
                .activityType(ActivityType.COMMENT_LIKE)
                .build();
    }
}
