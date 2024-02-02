package com.shop.newsfeedservice.dto.response;

import com.shop.newsfeedservice.domain.Follow;
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
                follow.getFromUser().getId(),
                follow.getToUser().getId()
        );
    }
}
