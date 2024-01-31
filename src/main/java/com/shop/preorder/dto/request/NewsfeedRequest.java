package com.shop.preorder.dto.request;

import com.shop.preorder.domain.Newsfeed;
import com.shop.preorder.domain.NewsfeedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedRequest {

    private Long userId;
    private Long activityId;
    private NewsfeedType newsfeedType;

    public Newsfeed toEntity() {
        return Newsfeed.builder()
                .userId(this.userId)
                .activityId(this.activityId)
                .newsfeedType(this.newsfeedType)
                .build();
    }
}
