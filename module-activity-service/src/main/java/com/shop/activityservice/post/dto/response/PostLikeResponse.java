package com.shop.activityservice.post.dto.response;

import com.shop.activityservice.post.entiity.PostLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeResponse {

    private Long id;
    private Long postId;
    private Long userId;

    public static PostLikeResponse of(PostLike postLike) {
        return new PostLikeResponse(
                postLike.getId(),
                postLike.getPost().getId(),
                postLike.getUserId()
        );
    }
}
