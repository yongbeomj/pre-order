package com.shop.newsfeedservice.newsfeed.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsfeedSearchRequest {

    private Long activityUserId;
    private Long targetId;
    private String activityType;

    @Builder
    public NewsfeedSearchRequest(Long activityUserId, Long targetId, String activityType) {
        this.activityUserId = activityUserId;
        this.targetId = targetId;
        this.activityType = activityType;
    }
}
