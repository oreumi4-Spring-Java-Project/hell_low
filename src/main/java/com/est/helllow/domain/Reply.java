package com.est.helllow.domain;

import com.est.helllow.domain.dto.ReplyResponseDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Table(name = "COMMENT")
@Getter
@Setter
@Entity
//@DynamicInsert// 모든 컬럼의 값이 null 이 아닌 경우에만 컬럼 포함
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COM_ID", updatable = false)
    private Long comId;

    @ManyToOne //게시글과 댓글은 다대일 관계
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @ManyToOne(targetEntity = User.class) // 사용자와 댓글은 다대일 관계
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COM_CONTENT",nullable = false,length = 500)
    private String content;

    @CreatedDate
    @Column(name = "COM_CREATED")
    private LocalDateTime comCreated;

    @LastModifiedDate
    @Column(name = "COM_MODIFIED")
    private LocalDateTime comModified;

    /*
    @PrePersist
    public void generateComId() {
        if (this.comId == null) {
            this.comId = "com_" + String.format("%04d", getNextCommentId());
        }
    }
    */

    private static int commentIdCounter = 0;

    private synchronized static int getNextCommentId() {
        return ++commentIdCounter;
    }

}