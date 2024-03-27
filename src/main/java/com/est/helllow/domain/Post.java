package com.est.helllow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    @Column(name="POST_ID", updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "POST_TITLE", nullable = false)
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "LIKE_COUNTS", nullable = false)
    private String likeCounts;

    @Column(name = "VIEW_COUNTS", nullable = false)
    private String viewCounts;

    @Column(name = "POST_FILE", nullable = false)
    private String postFile;

    @CreatedDate
    @Column(name = "POST_CREATED", nullable = false)
    private LocalDateTime postCreated;

    @LastModifiedDate
    @Column(name = "POST_MODIFIED")
    private LocalDateTime postModified;

}
