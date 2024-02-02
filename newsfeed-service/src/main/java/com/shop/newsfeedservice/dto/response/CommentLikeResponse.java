package com.shop.newsfeedservice.dto.response;

import com.shop.newsfeedservice.domain.CommentLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeResponse {

    private Long id;
    private Long commentId;
    private Long userId;

    public static CommentLikeResponse of(CommentLike commentLike) {
        return new CommentLikeResponse(
                commentLike.getId(),
                commentLike.getComment().getId(),
                commentLike.getUser().getId()
        );
    }
}
