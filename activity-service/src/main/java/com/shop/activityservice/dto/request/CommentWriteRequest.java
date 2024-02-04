package com.shop.activityservice.dto.request;

import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentWriteRequest {

    private String contents;

    public Comment toEntity(Post post, Long userId) {
        return Comment.builder()
                .contents(this.contents)
                .post(post)
                .userId(userId)
                .build();
    }

}
