package com.shop.newsfeedservice.domain;

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

    private Long activityUserId; // 로그인 유저 id (활동 유저)

    private Long targetUserId; // 대상 유저 id

    private Long targetId; // 활동 데이터 pk

    private String newsfeedType; // 뉴스피드 타입

    @Builder
    public Newsfeed(Long activityUserId, Long targetUserId, Long targetId, String newsfeedType) {
        this.activityUserId = activityUserId;
        this.targetUserId = targetUserId;
        this.targetId = targetId;
        this.newsfeedType = newsfeedType;
    }

}
