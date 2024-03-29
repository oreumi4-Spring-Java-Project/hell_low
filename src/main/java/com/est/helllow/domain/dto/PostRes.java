package com.est.helllow.domain.dto;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRes {
    private Post post;
    private List<Reply> replies;
}