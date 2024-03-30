package com.est.helllow.controller;

import com.est.helllow.domain.LikePost;
import com.est.helllow.service.LikePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/post/{postId}/like/{userId}")
public class LikePostController {

    private final LikePostService likePostService;

    /**
     * 게시물 좋아요 API
     * userId 로그인 기눙 구현 시 구조 수정 예정
     * ResDto 어떻게 할지 논의
     * @param postId
     * @param userId
     * @return
     */
    @PostMapping
    public ResponseEntity<LikePost> likePost(@PathVariable(name = "postId") Long postId,
                                             @PathVariable(name = "userId") Long userId) {
        LikePost likePost = likePostService.likePost(postId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(likePost);
    }

    /**
     * 게시물 좋아요 취소(삭제) 기능
     * userId 로그인 기눙 구현 시 구조 수정 예정
     *
     * @param postId
     * @param userId
     * @return
     */

    @DeleteMapping
    public ResponseEntity<LikePost> unLikePost(@PathVariable(name = "postId") Long postId,
                                               @PathVariable(name = "userId") Long userId){
        likePostService.unLikePost(postId,userId);
        return ResponseEntity.ok().build();
    }
}
