package com.shop.activityservice.dto.request;

import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
