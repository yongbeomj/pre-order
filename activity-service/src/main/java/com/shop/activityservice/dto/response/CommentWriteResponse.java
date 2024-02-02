package com.shop.activityservice.dto.response;

import com.shop.activityservice.domain.Comment;
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
