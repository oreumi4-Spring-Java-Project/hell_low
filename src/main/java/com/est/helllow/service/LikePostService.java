package com.est.helllow.service;

import com.est.helllow.domain.LikePost;
import com.est.helllow.domain.Post;
import com.est.helllow.domain.User;
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
    public LikePost likePost(Long postId, Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(null);// todo-> 예외처리 예정
        Post findPost = postRepository.findById(postId).orElseThrow(null);// todo-> 예외처리 예정

        // 게시물 존재 여부 체크
        if(findPost.getPostId()==null){
            log.error("해당 게시물 없음");// todo-> 예외처리 예정
        }

        LikePost existLikePost = likePostRepository.findLikePostByPost_PostIdAndUser_UserId(postId, userId);

        if(existLikePost !=null){
            log.error("이미 존재하는 게시물 좋아요 있음");// todo-> 예외처리 예정
        }

        LikePost likePost = new LikePost(findUser,findPost);
        return likePostRepository.save(likePost);

    }

    @Transactional
    public void unLikePost(Long postId,Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(null);// todo-> 예외처리 예정
        Post findPost = postRepository.findById(postId).orElseThrow(null);// todo-> 예외처리 예정

        // 게시물 존재 여부 체크
        if(findPost.getPostId()==null){
            log.error("해당 게시물 없음");// todo-> 예외처리 예정 / try catch 로?
        }

        LikePost existLikePost = likePostRepository.findLikePostByPost_PostIdAndUser_UserId(postId, userId);


        if(existLikePost ==null){
            log.error("존재하는 게시물 좋아요가 없음");// todo-> 예외처리 예정
        }

        likePostRepository.delete(existLikePost);
    }
}
