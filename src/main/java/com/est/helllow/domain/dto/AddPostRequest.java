package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddPostRequest {
    private String category;
    private String postTitle;
    private String postContent;

    public Post toEntity(){
        return Post.builder()
                .category(category)
                .title(postTitle)
                .content(postContent)
                .build();
    }
}
