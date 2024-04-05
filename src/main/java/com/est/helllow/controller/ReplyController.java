package com.est.helllow.controller;


import com.est.helllow.domain.Reply;
//import com.est.helllow.domain.dto.PostRes; // 게시물 조회용
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor

public class ReplyController {

    private final ReplyService replyService;

    //LSH

//    /**
//     * 게시물에 해당하는 댓글 리스트 조회 API
//     * @param postId
//     * @return
//     * @throws
//     */
//    @GetMapping("api/posts/{postId}")
//    @ResponseBody
//    public ResponseEntity<PostRes> getPost(@PathVariable(name = "postId")Long postId){
//       PostRes post=replyService.getPost(postId); // 유저 확인 고려 x
//        return ResponseEntity.ok(post);
//    }

//
//    @GetMapping("{postId}/comments")
//    @ResponseBody
//    public ResponseEntity<List<Reply>> getRepliesByPostId(@PathVariable(name = "postId")Long postId){
//        List<Reply> replies = replyService.getRepliesByPostId(postId);
//        return ResponseEntity.ok(replies);
//    }


    /**
     * 댓글 작성 API
     *
     * @param replyRequestDto
     * @param postId
     * @param userId
     * @return
     * @throws
     */
    @PostMapping("{postId}/{userId}/comments")
    @ResponseBody
    public BaseResponse replySave(@PathVariable(name = "postId") String postId,
                                  @PathVariable(name = "userId") String userId,
                                  @RequestBody ReplyRequestDto replyRequestDto) {
        try {
            Reply reply = replyService.replySave(postId, userId, replyRequestDto);
            ReplyResponseDto response = reply.toResponse();
            return new BaseResponse<>(response);
        } catch (BaseException exception) {
            return new BaseResponse(exception.getExceptionCode());
        }
    }

    /**
     * 댓글 삭제 API
     *
     * @param commentId
     * @param userId
     * @return
     * @throws
     */
    @DeleteMapping("{postId}/{userId}/comments/{commentId}")
    @ResponseBody
    public BaseResponse deleteReply(@PathVariable(name = "userId") String userId,
                                    @PathVariable(name = "commentId") String commentId) {
        try {
            String deletedComment = replyService.deleteComment(commentId, userId);
            return new BaseResponse<>(deletedComment + " 댓글이 삭제 되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    /**
     * 댓글 수정 API
     *
     * @param replyRequestDto
     * @param postId
     * @param userId
     * @return
     * @throws
     */
    @PutMapping("{postId}/{userId}/comments/{commentId}")
    public BaseResponse updateReply(@PathVariable(name = "postId") String postId,
                                    @PathVariable(name = "userId") String userId,
                                    @PathVariable(name = "commentId") String commentId,
                                    @RequestBody ReplyRequestDto replyRequestDto) {
        try {
            Reply reply = replyService.updateReply(postId, commentId, userId, replyRequestDto);
            ReplyResponseDto response = reply.toResponse();
            return new BaseResponse<>(response);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    //KMG

    /**
     * user가 작성한 reply 개수를 반환하는 API
     *
     * @param userId
     * @return Long
     * @author kmg
     */
    @GetMapping("api.hell-low.com/comment-management/users/{id}/count")
    @ResponseBody
    public BaseResponse mypostcount(@PathVariable(name = "id") String userId) {
        Long postCount = replyService.getReplyCountByUserId(userId);
        return new BaseResponse(postCount);
    }

    /**
     * user가 작성한 reply의 정보를 반환하는 API
     *
     * @param userId
     * @return List<Reply>
     * @author kmg
     */
    @GetMapping("api.hell-low.com/comment-management/users/{id}")
    @ResponseBody
    public BaseResponse getMyReplys(@PathVariable(name = "id") String userId) {
        List<ReplyResponseDto> replyList = replyService.getMyReplys(userId)
                .stream().map(ReplyResponseDto::new)
                .toList();
        return new BaseResponse<>(replyList);
    }

}
