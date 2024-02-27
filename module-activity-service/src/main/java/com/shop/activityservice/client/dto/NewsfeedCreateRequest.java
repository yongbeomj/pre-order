package com.shop.activityservice.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsfeedCreateRequest {

    private Long activityUserId;
    private Long targetUserId;
    private Long targetId;
    private String activityType;

    @Builder
    public NewsfeedCreateRequest(Long activityUserId, Long targetUserId, Long targetId, ActivityType activityType) {
        this.activityUserId = activityUserId;
        this.targetUserId = targetUserId;
        this.targetId = targetId;
        this.activityType = activityType.toString();
    }
}
