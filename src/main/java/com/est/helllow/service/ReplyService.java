package com.est.helllow.service;


import com.est.helllow.domain.Post;
import com.est.helllow.domain.Reply;
import com.est.helllow.domain.User;
//import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public void deleteCommentByPostId(String postId){
        replyRepository.deleteByPost_PostId(postId);
    }
    @Transactional
    public void deleteCommentsByUserId(String userId) {
        replyRepository.deleteByUser_UserId(userId);
    }

    @Transactional
    public Reply replySave(String postId, String userId, ReplyRequestDto replyRequestDto) throws BaseException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_INVALID_USER));
        Reply reply = replyRequestDto.toEntity(post, user);

        return replyRepository.save(reply);
    }

    @Transactional
    public String deleteComment(String commentId, String userId) throws BaseException {
        Reply findReply = replyRepository.findById(commentId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_REPLY));

        // 예외처리 예정 부분
        // 해당 댓글 작성자 여부 판단
        validateReply(userId, findReply);

        replyRepository.delete(findReply);
        return commentId;
    }

    @Transactional
    public Reply updateReply(String postId, String commentId, String userId, ReplyRequestDto replyRequestDto) throws BaseException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_POST));
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_INVALID_USER));
        Reply reply = replyRepository.findById(commentId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_EXIST_REPLY));

        validateReply(userId, reply);


        Reply modifiedReply = replyRequestDto.toEntity(post, user);
        Reply findReply = replyRepository.findById(commentId).orElseThrow();
        findReply.updateReply(modifiedReply.getContent());

        return reply;
    }


    // 댓글 작성자 판단 및 댓글 존재 여부 판단
    private static void validateReply(String userId, Reply findReply) throws BaseException {
        if (!findReply.getUser().getUserId().equals(userId)) {
            throw new BaseException(BaseExceptionCode.NOT_INVALID_USER);
        }

        // 댓글 존재 여부
        if (findReply.getComId() == null) {
            throw new BaseException(BaseExceptionCode.NOT_EXIST_REPLY);
        }
    }

    //user가 작성한 reply 개수 - KMG
    public Long getReplyCountByUserId(String userId) {
        return replyRepository.countAllByUser_userId(userId);
    }

    //user가 작성한 reply 정보 - KMG
    public List<Reply> getMyReplys(String userId) {
        return replyRepository.findAllByUser_UserId(userId);
    }
}
