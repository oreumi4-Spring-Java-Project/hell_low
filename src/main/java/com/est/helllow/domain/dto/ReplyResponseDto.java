package com.est.helllow.domain.dto;

import com.est.helllow.domain.Reply;
import lombok.Builder;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;

public class ReplyResponseDto {
    private String comId;
    private String content;

    private LocalDateTime comCreated;
    private LocalDateTime comModified;
    private String nickname;
    private String postId;
    @Builder
    public ReplyResponseDto (Reply reply){
        this.comId=reply.getComId();
        this.content=reply.getContent();
        this.comCreated=reply.getComCreated();
        this.nickname=reply.getUser().getUserName();
        this.postId=reply.getPost().getPost_id();
    }

}
