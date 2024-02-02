package com.shop.activityservice.dto.request;

import com.shop.activityservice.domain.Post;
import com.shop.activityservice.domain.User;
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
