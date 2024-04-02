package com.est.helllow.dto;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReplyDTO {
    private String comId;
    private Post post;
    private User user;
    private String content;
    private LocalDateTime comCreated;
    private LocalDateTime comModified;


    public static ReplyDTO toDTO(Reply reply) {
        return ReplyDTO.builder()
                .comId(reply.getComId())
                .post(reply.getPost())
                .user(reply.getUser())
                .content(reply.getContent())
                .comCreated(reply.getComCreated())
                .comModified(reply.getComModified())
                .build();

    }
}
