package com.est.helllow.service;

import com.est.helllow.domain.LikePost;
import com.est.helllow.domain.Post;
import com.est.helllow.domain.User;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.LikePostRepository;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikePostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;

    @Transactional
    public int likePost(String postId, String userId) throws BaseException {
        User findUser = userRepository.findById(userId).orElseThrow(()->new BaseException(BaseExceptionCode.NOT_INVALID_USER));
        Post findPost = postRepository.findById(postId).orElseThrow(()->new BaseException(BaseExceptionCode.NOT_EXIST_POST));

        // 게시물 존재 여부 체크
        if(findPost.getPostId()==null){
            throw new BaseException(BaseExceptionCode.NOT_EXIST_POST);
        }

        LikePost existLikePost = likePostRepository.findLikePostByPost_PostIdAndUser_UserId(postId, userId);

        if(existLikePost !=null){
            throw new BaseException(BaseExceptionCode.FAILED_TO_LIKE_POST);
        }

        LikePost likePost = new LikePost(findUser,findPost);
        likePostRepository.save(likePost);
        return findPost.incLikeCount();
    }

    @Transactional
    public int unLikePost(String postId,String userId) throws BaseException {
        User findUser = userRepository.findById(userId).orElseThrow(null);// todo-> 예외처리 예정
        Post findPost = postRepository.findById(postId).orElseThrow(null);// todo-> 예외처리 예정

        // 게시물 존재 여부 체크
        if(findPost.getPostId()==null){
            throw new BaseException(BaseExceptionCode.NOT_EXIST_POST);
        }

        LikePost existLikePost = likePostRepository.findLikePostByPost_PostIdAndUser_UserId(postId, userId);


        if(existLikePost ==null){
            throw new BaseException(BaseExceptionCode.FAILED_TO_UNLIKE_POST);
        }

        likePostRepository.delete(existLikePost);
        return findPost.decLikeCount();
    }
}
