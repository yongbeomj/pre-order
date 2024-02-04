package com.shop.activityservice.dto.response;

import com.shop.activityservice.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteResponse {

    private Long id;
    private String title;
    private Long writerId;

    public static PostWriteResponse of(Post post) {
        return new PostWriteResponse(
                post.getId(),
                post.getTitle(),
                post.getUserId()
        );
    }

}
