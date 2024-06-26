package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String category;
    private String postTitle;
    private String postContent;

    public PostRequestDto(String category, String title, String content){
        this.category = category;
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

    public Post toEntity(User user, String imgURL){
        return Post.builder()
                .category(category)
                .title(postTitle)
                .content(postContent)
                .file(imgURL)
                .user(user)
                .build();
    }
}
