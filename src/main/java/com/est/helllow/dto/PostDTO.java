package com.est.helllow.dto;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDTO {
    private Long postId;
    private UserDTO user;
    private String category;
    private String postTitle;
    private String postContent;
    private int likeCounts;
    private int viewCounts;
    private String postFile;
    private LocalDateTime postCreated;
    private LocalDateTime postModified;

    public static PostDTO toDTO(Post post) {
        return PostDTO.builder()
                .postId(post.getPostId())
                .postModified(post.getPostModified())
                .postContent(post.getPostContent())
                .postCreated(post.getPostCreated())
                .postFile(post.getPostFile())
                .postTitle(post.getPostTitle())
                .category(post.getCategory())
                .likeCounts(post.getLikeCounts())
                .viewCounts(post.getViewCounts())
                .user(UserDTO.toDTO(post.getUser()))   //post.getUser()
                .build();
    }
}
