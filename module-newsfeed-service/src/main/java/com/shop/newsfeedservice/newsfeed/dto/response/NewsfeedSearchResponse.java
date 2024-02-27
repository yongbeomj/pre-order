package com.shop.newsfeedservice.newsfeed.dto.response;

import com.shop.newsfeedservice.newsfeed.entity.Newsfeed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedSearchResponse {

    private Long loginUserId;
    private Long activityUserId;
    private Long targetUserId;
    private String newsfeedType;
    private String createdAt;

    public static NewsfeedSearchResponse of(Newsfeed newsfeed, Long principalId) {
        return new NewsfeedSearchResponse(
                principalId,
                newsfeed.getActivityUserId(),
                newsfeed.getTargetUserId(),
                newsfeed.getNewsfeedType(),
                newsfeed.getCreatedAt().toString()
        );
    }

}
