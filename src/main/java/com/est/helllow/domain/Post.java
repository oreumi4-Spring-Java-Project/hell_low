package com.est.helllow.domain;

import com.est.helllow.domain.dto.AddPostResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    feature/create_post_api
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
  
    /*
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "post_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID post_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
     */

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

    @CreatedDate
    @Column(name = "POST_CREATED")

    private LocalDateTime postCreated;

    @LastModifiedDate
    @Column(name = "POST_MODIFIED")
    private LocalDateTime postModified;
  
    
    @Builder
    public Post(String category, String title, String content) {
        this.category = category;
        this.postTitle = title;
        this.postContent = content;
    }

    public AddPostResponse toResponse() {
        return AddPostResponse.builder()
                .category(category)
                .postTitle(postTitle)
                .postContent(postContent)
                .build();
    }

    //디폴트값 설정
    @PrePersist
    public void prePersist() {
        this.likeCounts = this.likeCounts == null ? 0 : this.likeCounts;
        this.viewCounts = this.viewCounts == null ? 0 : this.viewCounts;
        this.postFile = this.postFile == null ? "logo.png" : this.postFile;
    }

}
