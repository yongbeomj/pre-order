package com.shop.preorder.dto.response;

import com.shop.preorder.domain.Follow;
import com.shop.preorder.domain.Newsfeed;
import com.shop.preorder.domain.NewsfeedType;
import com.shop.preorder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedResponse {

    private Long activityUserId;
    private String activityUserName;
    private Long targetUserId; // 팔로우 id
    private String targetUserName; // 팔로우 유저 이름
    private NewsfeedType newsfeedType;
    private LocalDateTime createdAt;
    private Object content;

    public static NewsfeedResponse of(Newsfeed newsfeed, Object content) {
        return new NewsfeedResponse(
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
