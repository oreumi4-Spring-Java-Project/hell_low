package com.est.helllow.service;


import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Reply replySave(Long postId,Long userId,ReplyRequestDto replyRequestDto){
    Post post = postRepository.findById(postId).orElseThrow(null); //todo -> 예외처리 예정 (컨트롤러쪽으로 예외전파??)
    User user = userRepository.findById(userId).orElseThrow(null);//todo -> 예외처리 예정
    Reply reply = replyRequestDto.toEntity(post,user);

        return replyRepository.save(reply);
    }

}
