package com.est.helllow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "LIKE")
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID",nullable = false)
    private Post post;

    @CreatedDate
    @Column(name = "LIKE_CREATED")
    private LocalDateTime likeCreated;

    @LastModifiedDate
    @Column(name = "LIKE_MODIFIED")
    private LocalDateTime likeModified;
}
