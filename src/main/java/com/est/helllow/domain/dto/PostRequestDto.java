package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String category;
    private String postTitle;
    private String postContent;

    public PostRequestDto(String title, String content){
        this.postTitle = title;
        this.postContent = content;
    }

    public Post toEntity(){
        return Post.builder()
                .category(category)
                .title(postTitle)
                .content(postContent)
                .build();
    }
}
