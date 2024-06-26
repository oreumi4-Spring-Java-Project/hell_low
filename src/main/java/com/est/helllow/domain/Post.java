package com.est.helllow.domain;

import com.est.helllow.config.BaseTimeEntity;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostResponseDto;
import com.est.helllow.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseTimeEntity {

    @Builder
    public Post(String category, String title, String content, String file, User user) {
        this.category = category;
        this.postTitle = title;
        this.postContent = content;
        this.postFile = file;
        this.user = user;
    }

    @Id
    @Column(name = "POST_ID", updatable = false,length = 100)
    private String postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CATEGORY", nullable = false,length = 20)
    private String category;

    @Column(name = "POST_TITLE", nullable = false,length = 50)
    private String postTitle;

    @Column(name = "POST_CONTENT", nullable = false,length = 3000)
    private String postContent;

    @Column(name = "LIKE_COUNTS")
    private Integer likeCounts;

    @Column(name = "VIEW_COUNTS")
    private Integer viewCounts;

    @Column(name = "POST_FILE")
    private String postFile;



    public PostResponseDto toResponse() {
        return PostResponseDto.builder()
                .category(category)
                .postTitle(postTitle)
                .postContent(postContent)
                .likeCounts(likeCounts)
                .viewCounts(viewCounts)
                .postFile(postFile)
                .postCreated(getCreatedAt())
                .postModified(getModifiedAt())
                .build();
    }

    public void update(PostRequestDto request, String imgUrl) {
        this.category = request.getCategory();
        this.postTitle = request.getPostTitle();
        this.postContent = request.getPostContent();
        this.postFile = imgUrl;
    }

    public int incLikeCount() {
        return ++likeCounts;
    }

    public int decLikeCount() {
        return --likeCounts;
    }

    //Default Setting
    @PrePersist
    public void prePersist() {
        this.postId = IdGenerator.generatePostId(this.category);
        this.likeCounts = this.likeCounts == null ? 0 : this.likeCounts;
        this.viewCounts = this.viewCounts == null ? 0 : this.viewCounts;
    }

}
