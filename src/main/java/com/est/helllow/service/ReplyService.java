package com.est.helllow.service;


import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
  
    public Reply replySave(Long postId,Long userId,ReplyRequestDto replyRequestDto){
    Post post = postRepository.findById(postId).orElseThrow(null); //todo -> 예외처리 예정
    User user = userRepository.findById(userId).orElseThrow(null);//todo -> 예외처리 예정
    Reply reply = replyRequestDto.toEntity(post,user);

        return replyRepository.save(reply);
    }

    public void deleteComment(Long userId) {

    }

//    public boolean isReplyWriter(Long postId,Long userId,Long commentId){
//        Optional<Post> validate1 = postRepository.findById(postId);
//        if(!validate1.isPresent()){
//            return false; // 해당하는 댓글 x, 해당 게시물의 댓글 아님
//        }
//        Post post = validate1.get();
//
//        replyRepository.find(commentId,post);
//
//
//    }
}
