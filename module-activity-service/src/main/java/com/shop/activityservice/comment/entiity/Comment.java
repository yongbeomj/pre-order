package com.shop.activityservice.comment.entiity;

import com.shop.activityservice.common.entity.BaseTimeEntity;
import com.shop.activityservice.client.dto.ActivityType;
import com.shop.activityservice.post.entiity.Post;
import com.shop.activityservice.client.dto.NewsfeedCreateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    private Long userId;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @Builder
    public Comment(String contents, Post post, Long userId) {
        this.contents = contents;
        this.post = post;
        this.userId = userId;
    }

    public NewsfeedCreateRequest newsfeedCreateRequest(Long activityUserId) {
        return NewsfeedCreateRequest.builder()
                .activityUserId(activityUserId)
                .targetUserId(userId)
                .targetId(id)
                .activityType(ActivityType.COMMENT)
                .build();
    }
}
