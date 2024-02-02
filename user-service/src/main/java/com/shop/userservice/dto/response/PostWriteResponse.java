package com.shop.userservice.dto.response;

import com.shop.userservice.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteResponse {

    private Long id;
    private String title;
    private String writer;

    public static PostWriteResponse of(Post post) {
        return new PostWriteResponse(
                post.getId(),
                post.getTitle(),
                post.getUser().getEmail()
        );
    }

}
