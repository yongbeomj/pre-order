package com.shop.preorder.dto.response;

import com.shop.preorder.domain.Post;
import com.shop.preorder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
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
                post.getWriter().getEmail()
        );
    }

}
