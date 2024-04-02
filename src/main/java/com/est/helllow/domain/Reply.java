package com.est.helllow.domain;

import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "COMMENT")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Reply {

    @Id
    @Column(name = "COM_ID", updatable = false)
    private String comId;

    @ManyToOne //게시글과 댓글은 다대일 관계
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @ManyToOne(targetEntity = User.class) // 사용자와 댓글은 다대일 관계
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COM_CONTENT",nullable = false,length = 500)
    private String content;

    @CreatedDate
    @Column(name = "COM_CREATED",updatable = false)// 생성일은 업데이트 x
    private LocalDateTime comCreated;

    @LastModifiedDate
    @Column(name = "COM_MODIFIED")
    private LocalDateTime comModified;

    public ReplyResponseDto toResponse(){
        return new ReplyResponseDto().builder()
                .postId(post.getPostId())
                .nickname(user.getUserName())
                .comCreated(comCreated)
                .comModified(comModified)
                .content(content)
                .comId(comId)
                .build();
    }
    public void updateReply(String content){
        this.content=content;
    }

    @PrePersist
    public void prePersist() {
        this.comId= IdGenerator.generateCommentId(this.post.getCategory());
    }

}