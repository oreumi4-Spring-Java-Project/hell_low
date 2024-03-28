package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AddPostResponse {
    private String category;
    private String postTitle;
    private String postContent;

    public AddPostResponse(Post post){
        this.category = post.getCategory();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
    }
}
