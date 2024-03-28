package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyRequestDto {
    private String comId;
    private String content;
    private LocalDateTime comCreated;
    private LocalDateTime comModified;
    private User user;
    private Post post;

    public Reply toEntity(String postId){
        Reply reply=Reply.builder()
                .comId(comId)
                .content(content)
                .comCreated(comCreated)
                .comModified(comModified)
                .user(user)
                .post(post)
                .build();
        return reply;
    }
}
