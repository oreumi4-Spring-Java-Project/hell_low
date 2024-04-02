package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private String category;
    private String postTitle;
    private String postContent;
    private Integer likeCounts;
    private Integer viewCounts;
    private String postFile;
    private LocalDateTime postCreated;
    private LocalDateTime postModified;


    public PostResponseDto(Post post){
        this.category = post.getCategory();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.likeCounts = post.getLikeCounts();
        this.viewCounts = post.getViewCounts();
        this.postFile = post.getPostFile();
        this.postCreated = post.getCreatedAt();
        this.postModified = post.getModifiedAt();
    }
}
