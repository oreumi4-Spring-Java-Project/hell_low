package com.est.helllow.domain.dto;

import com.est.helllow.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class ReplyResponseDto {
    private Long comId;
    private String content;
    private String nickname;
    private Long postId;
    private LocalDateTime comCreated;
    private LocalDateTime comModified;
    @Builder
    public ReplyResponseDto(Long comId,
                            String content,
                            String nickname,
                            Long postId,
                            LocalDateTime comCreated,
                            LocalDateTime comModified) {
        this.comId = comId;
        this.content = content;
        this.nickname = nickname;
        this.postId = postId;
        this.comCreated = comCreated;
        this.comModified= comModified;
    }
}
