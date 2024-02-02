package com.shop.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User activityUser; // 로그인 유저 id (활동 유저)

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser; // 대상 유저 id

    private Long targetId; // 활동 데이터 pk

    @Enumerated(EnumType.STRING)
    private NewsfeedType newsfeedType; // 뉴스피드 타입

    @Builder
    public Newsfeed(User activityUser, User targetUser, Long targetId, NewsfeedType newsfeedType) {
        this.activityUser = activityUser;
        this.targetUser = targetUser;
        this.targetId = targetId;
        this.newsfeedType = newsfeedType;
    }
}
