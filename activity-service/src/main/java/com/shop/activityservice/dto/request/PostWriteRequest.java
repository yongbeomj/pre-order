package com.shop.activityservice.dto.request;

import com.shop.activityservice.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostWriteRequest {

    private String title;
    private String contents;

    public Post toEntity(Long userId) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .userId(userId)
                .build();
    }

}
