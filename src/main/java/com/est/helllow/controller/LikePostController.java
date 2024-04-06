package com.est.helllow.controller;

import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.LikePostService;
import com.est.helllow.service.UserGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api.hell-low.com/like-management/{postId}/likes/{userId}")
public class LikePostController {

    private final LikePostService likePostService;
    private final UserGradeService userGradeService;

    /**
     * 게시물 좋아요 API
     * userId 로그인 기눙 구현 시 구조 수정 예정
     * ResDto 어떻게 할지 논의
     * @param postId
     * @param userId
     * @return
     */
    @PostMapping
    @ResponseBody
    public BaseResponse likePost(@PathVariable(name = "postId") String postId,
                                             @PathVariable(name = "userId") String userId) {
        try {
            int likePostCount = likePostService.likePost(postId, userId);
            userGradeService.upgradeUserGrade(userId);
            return new BaseResponse<>(likePostCount);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getExceptionCode());
        }
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
    @ResponseBody
    public BaseResponse unLikePost(@PathVariable(name = "postId") String postId,
                                   @PathVariable(name = "userId") String userId){
        try {
            int likePostCount = likePostService.unLikePost(postId, userId);
            return new BaseResponse<>(likePostCount);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }
}
