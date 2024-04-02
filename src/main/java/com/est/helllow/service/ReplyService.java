package com.est.helllow.service;


import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
//import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


//    // 게시물 조회용
//    public PostRes getPost(Long postId){
//        Post findPost = postRepository.findById(postId).orElseThrow(null);// todo -> 예외처리 예정
//
//
//    }
//
//
//    public List<Reply> getRepliesByPostId(Long postId){
//        return replyRepository.findByPost_PostId(postId);
//    }

    @Transactional
    public Reply replySave(Long postId,Long userId,ReplyRequestDto replyRequestDto){
    Post post = postRepository.findById(postId).orElseThrow(null); //todo -> 예외처리 예정 (컨트롤러쪽으로 예외전파??)
    User user = userRepository.findById(userId).orElseThrow(null);//todo -> 예외처리 예정
    Reply reply = replyRequestDto.toEntity(post,user);

        return replyRepository.save(reply);
    }

    @Transactional
    public Long deleteComment(Long commentId,Long userId) {
        Reply findReply = replyRepository.findById(commentId).orElseThrow(null);//todo -> 예외처리 예정

        // 예외처리 예정 부분
        // 해당 댓글 작성자 여부 판단
        validateReply(userId, findReply);

        replyRepository.delete(findReply);
        return commentId;
    }

    @Transactional
    public Reply updateReply(Long postId,Long commentId,Long userId,ReplyRequestDto replyRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(null);//todo -> 예외처리 예정
        User user = userRepository.findById(userId).orElseThrow(null);//todo -> 예외처리 예정
        Reply reply = replyRepository.findById(commentId).orElseThrow(null);//todo -> 예외처리 예정
        validateReply(userId, reply);


        Reply modifiedReply = replyRequestDto.toEntity(post, user);
        Reply findReply = replyRepository.findById(commentId).orElseThrow();
        findReply.updateReply(modifiedReply.getContent());

        return reply;
    }


    // 댓글 작성 or 수정 시
    // 댓글 작성자 판단 및 댓글 존재 여부 판단
    private static void validateReply(Long userId, Reply findReply) {
        if(!findReply.getUser().equals(userId)){
            log.error("예외 발생");
        }

        // 댓글 존재 여부
        if(findReply.getComId()==null){
            log.error("예외 발생");
        }
    }



}
