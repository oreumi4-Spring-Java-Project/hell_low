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
    private String post_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "POST_TITLE", nullable = false)
    private String post_title;

    @Column(name = "POST_CONTENT")
    private String post_content;

    @Column(name = "LIKE_COUNTS", nullable = false)
    private String like_counts;

    @Column(name = "VIEW_COUNTS", nullable = false)
    private String view_counts;

    @Column(name = "POST_FILE", nullable = false)
    private String post_file;

    @CreatedDate
    @Column(name = "POST_CREATED", nullable = false)
    private LocalDateTime post_created;

    @LastModifiedDate
    @Column(name = "POST_MODIFIED")
    private LocalDateTime post_modified;

}
