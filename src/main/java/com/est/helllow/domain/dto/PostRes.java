package com.est.helllow.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRes {
    private PostResponseDto post;
    private List<ReplyResponseDto> replies;
}