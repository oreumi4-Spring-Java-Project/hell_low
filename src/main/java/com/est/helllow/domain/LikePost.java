package com.est.helllow.domain;

import com.est.helllow.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LIKE_POST") // MYSQL 에서 LIKE가 예약어
@EntityListeners(AuditingEntityListener.class)
public class LikePost {
    @Id
    private String likeId;

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

    @PrePersist
    public void prePersist(){
        this.likeId= IdGenerator.generateLikePostId(this.post.getCategory());
    }
}
