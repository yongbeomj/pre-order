package com.shop.activityservice.follow.dto.response;

import com.shop.activityservice.follow.entiity.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponse {

    private Long id;
    private Long fromUserId;
    private Long toUserId;

    public static FollowResponse of(Follow follow) {
        return new FollowResponse(
                follow.getId(),
                follow.getFromUserId(),
                follow.getToUserId()
        );
    }
}
