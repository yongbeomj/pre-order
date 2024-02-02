package com.shop.newsfeedservice.dto.request;

import com.shop.newsfeedservice.domain.Post;
import com.shop.newsfeedservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteRequest {

    private String title;
    private String contents;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .user(user)
                .build();
    }

}
