package com.shop.userservice.dto.request;

import com.shop.userservice.domain.Comment;
import com.shop.userservice.domain.Post;
import com.shop.userservice.domain.User;
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
