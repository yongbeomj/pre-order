package com.shop.preorder.dto.response;

import com.shop.preorder.domain.Comment;
import com.shop.preorder.domain.Post;
import com.shop.preorder.domain.User;
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
