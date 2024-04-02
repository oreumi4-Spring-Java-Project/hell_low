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
    private String comId;
    private String content;
    private String nickname;
    private String postId;
    private LocalDateTime comCreated;
    private LocalDateTime comModified;
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
        this.comModified= comModified;
    }
}
