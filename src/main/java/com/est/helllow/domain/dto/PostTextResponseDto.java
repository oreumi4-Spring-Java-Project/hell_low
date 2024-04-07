package com.est.helllow.domain.dto;

import java.time.LocalDateTime;

import com.est.helllow.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostTextResponseDto {
	private String postId;
	private String category;
	private String postTitle;
	private Integer likeCounts;
	private Integer viewCounts;
	private String postFile;

	public PostTextResponseDto(Post post) {
		this.postId = post.getPostId();
		this.category = post.getCategory();
		this.postTitle = post.getPostTitle();
		this.likeCounts = post.getLikeCounts();
		this.viewCounts = post.getViewCounts();
		this.postFile = post.getPostFile();
	}
}
