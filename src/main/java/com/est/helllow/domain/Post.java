package com.est.helllow.domain;

import com.est.helllow.config.BaseTimeEntity;
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

    @Id
    @Column(name = "POST_ID", updatable = false)
    private String postId;

//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name="uuid2", strategy = "uuid2")
//    @Column(name = "post_id", columnDefinition = "BINARY(16)", nullable = false)
//    private UUID post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "POST_TITLE", nullable = false)
    private String postTitle;

    @Column(name = "POST_CONTENT", nullable = false)
    private String postContent;

    @Column(name = "LIKE_COUNTS")
    private Integer likeCounts;

    @Column(name = "VIEW_COUNTS")
    private Integer viewCounts;

    @Column(name = "POST_FILE")
    private String postFile;

    @Builder
    public Post(String category, String title, String content, String file) {
        this.category = category;
        this.postTitle = title;
        this.postContent = content;
        this.postFile = file;
    }

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

    public void update(String title, String content) {
        this.postTitle = title;
        this.postContent = content;
    }

    public int incLikeCount() {
        return ++likeCounts;
    }

    public int decLikeCount() {
        return --likeCounts;
    }

    @PrePersist
    public void prePersist() {
        this.postId = IdGenerator.generatePostId(this.category);
        //    디폴트값 설정
        this.likeCounts = this.likeCounts == null ? 0 : this.likeCounts;
        this.viewCounts = this.viewCounts == null ? 0 : this.viewCounts;
        //this.postFile = this.postFile == null ? "logo.png" : this.postFile;
    }

}
