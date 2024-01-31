package com.shop.preorder.dto.request;

import com.shop.preorder.domain.Post;
import com.shop.preorder.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteRequest {

    private String title;
    private String contents;

    public Post toEntity(User writer) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .writer(writer)
                .build();
    }

}
