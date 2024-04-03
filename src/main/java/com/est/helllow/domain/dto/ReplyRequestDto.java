package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyRequestDto {
    private String comId;
    private String content;

    public Reply toEntity(Post post, User user) {
        return Reply.builder()
                .content(content)
                .user(user)
                .post(post)
                .build();
    }
}
