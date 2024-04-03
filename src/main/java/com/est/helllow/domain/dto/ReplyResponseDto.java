package com.est.helllow.domain.dto;

import com.est.helllow.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReplyResponseDto {
    private String comId;
    private String content;
    private String nickname;
    private String postId;
    private LocalDateTime comCreated;
    private LocalDateTime comModified;

    public ReplyResponseDto(Reply reply) {
        this.comId = reply.getComId();
        this.content = reply.getContent();
        this.comCreated = reply.getCreatedAt();
        this.comModified = reply.getModifiedAt();
    }

    @Builder
    public ReplyResponseDto(String comId,
                            String content,
                            String nickname,
                            String postId,
                            LocalDateTime comCreated,
                            LocalDateTime comModified) {
        this.comId = comId;
        this.content = content;
        this.nickname = nickname;
        this.postId = postId;
        this.comCreated = comCreated;
        this.comModified = comModified;
    }
}
