package com.est.helllow.domain.dto;

import com.est.helllow.domain.Reply;
import lombok.Builder;
import lombok.Getter;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
@Getter
public class ReplyResponseDto {
    private Long comId;
    private String content;
    private String nickname;
    private Long postId;
    private LocalDateTime comCreated;

    @Builder
    public ReplyResponseDto (Reply reply){
        this.comId=reply.getComId();
        this.content=reply.getContent();
        this.nickname=reply.getUser().getUserName();
        this.postId=reply.getPost().getPostId();
        this.comCreated=reply.getComCreated();
    }

}
