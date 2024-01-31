package com.shop.preorder.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "newsfeeds")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Newsfeed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long activityId; // 유저 활동 데이터 pk

    @Enumerated(EnumType.STRING)
    private NewsfeedType newsfeedType;

    @Builder
    public Newsfeed(Long userId, Long activityId, NewsfeedType newsfeedType) {
        this.userId = userId;
        this.activityId = activityId;
        this.newsfeedType = newsfeedType;
    }
}
