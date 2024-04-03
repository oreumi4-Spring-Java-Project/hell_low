package com.est.helllow.domain;

import com.est.helllow.config.BaseTimeEntity;
import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "COMMENT")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Reply extends BaseTimeEntity {

    @Id
    @Column(name = "COM_ID", updatable = false)
    private String comId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 (엔티티 사용 전까지 연관 엔티티 로딩 x)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class) // 사용자와 댓글은 다대일 관계
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COM_CONTENT", nullable = false, length = 500)
    private String content;


    public ReplyResponseDto toResponse() {
        return new ReplyResponseDto().builder()
                .postId(post.getPostId())
                .nickname(user.getUserName())
                .comCreated(getCreatedAt())
                .comModified(getModifiedAt())
                .content(content)
                .comId(comId)
                .build();
    }

    public void updateReply(String content) {
        this.content = content;
    }

    @PrePersist
    public void prePersist() {
        this.comId = IdGenerator.generateCommentId(this.post.getCategory());
    }

}