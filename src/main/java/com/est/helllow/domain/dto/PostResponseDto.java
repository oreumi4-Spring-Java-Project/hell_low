package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private String category;
    private String postTitle;
    private String postContent;

    public PostResponseDto(Post post){
        this.category = post.getCategory();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
    }
}
