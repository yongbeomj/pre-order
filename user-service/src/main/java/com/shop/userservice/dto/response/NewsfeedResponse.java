package com.shop.userservice.dto.response;

import com.shop.userservice.domain.Newsfeed;
import com.shop.userservice.domain.NewsfeedType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedResponse {

    private Long currentUserId; // 로그인 유저
    private Long activityUserId;
    private String activityUserName;
    private Long targetUserId;
    private String targetUserName;
    private NewsfeedType newsfeedType;
    private LocalDateTime createdAt;
    private Object content;

    public static NewsfeedResponse of(Newsfeed newsfeed, Long currentUserId, Object content) {
        return new NewsfeedResponse(
                currentUserId,
                newsfeed.getActivityUser().getId(),
                newsfeed.getActivityUser().getName(),
                newsfeed.getTargetUser().getId(),
                newsfeed.getTargetUser().getName(),
                newsfeed.getNewsfeedType(),
                newsfeed.getCreatedAt(),
                content
        );
    }

}
