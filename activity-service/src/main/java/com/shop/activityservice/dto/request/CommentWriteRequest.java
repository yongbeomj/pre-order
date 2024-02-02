package com.shop.activityservice.dto.request;

import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.Post;
import com.shop.activityservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteRequest {

    private String contents;

    public Comment toEntity(Post post, User user) {
        return Comment.builder()
                .contents(this.contents)
                .post(post)
                .user(user)
                .build();
    }

}
