package com.shop.activityservice.comment.dto.response;

import com.shop.activityservice.comment.entiity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteResponse {

    private Long id;

    public static CommentWriteResponse of(Comment comment) {
        return new CommentWriteResponse(
                comment.getId()
        );
    }

}
