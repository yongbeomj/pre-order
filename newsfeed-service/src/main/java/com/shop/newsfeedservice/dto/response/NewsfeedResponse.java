package com.shop.newsfeedservice.dto.response;


import com.shop.newsfeedservice.domain.Newsfeed;
import com.shop.newsfeedservice.domain.NewsfeedType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedResponse {

    private Long loginUserId; // 로그인 유저
    private Long activityUserId;
    private String activityUserName;
    private Long targetUserId;
    private String targetUserName;
    private NewsfeedType newsfeedType;
    private LocalDateTime createdAt;
    private Object content;

    public static NewsfeedResponse of(Newsfeed newsfeed, Long loginUserId, Object content) {
        return new NewsfeedResponse(
                loginUserId,
                newsfeed.getActivityUserId(),
                null,
                newsfeed.getTargetUserId(),
                null,
                newsfeed.getNewsfeedType(),
                newsfeed.getCreatedAt(),
                content
        );
    }

}
