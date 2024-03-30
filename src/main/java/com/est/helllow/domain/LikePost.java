package com.est.helllow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LIKE_POST") // MYSQL 에서 LIKE가 예약어
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
    @Column(name = "LIKE_CREATED",updatable = false)
    private LocalDateTime likeCreated;

    @LastModifiedDate // 필요는 없지만 나중에 공통화 작업 고려
    @Column(name = "LIKE_MODIFIED")
    private LocalDateTime likeModified;

    public LikePost(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
