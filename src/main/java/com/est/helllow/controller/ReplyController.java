package com.est.helllow.controller;


import com.est.helllow.domain.Reply;
//import com.est.helllow.domain.dto.PostRes; // 게시물 조회용
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.dto.ReplyDTO;
import com.est.helllow.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

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
     * @param replyRequestDto
     * @param postId
     * @param userId
     * @return
     * @throws
     */

    @PostMapping("{postId}/{userId}/comments")
    @ResponseBody
    public ResponseEntity<ReplyResponseDto> replySave(@PathVariable(name = "postId") String postId,
                                                      @PathVariable(name = "userId")Long userId,
                                                      @RequestBody ReplyRequestDto replyRequestDto){
        Reply reply = replyService.replySave(postId,userId,replyRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(reply.toResponse());
    }

    /**
     * 댓글 삭제 API
     * @param commentId
     * @param userId
     * @return
     * @throws
     */
    @DeleteMapping("{postId}/{userId}/comments/{commentId}")
    @ResponseBody
    public ResponseEntity<Void> deleteReply(@PathVariable(name = "userId")Long userId,
                                            @PathVariable(name = "commentId")String commentId){
        replyService.deleteComment(commentId,userId);
        return ResponseEntity.ok().build();
    }

    /**
     * 댓글 수정 API
     * @param replyRequestDto
     * @param postId
     * @param userId
     * @return
     * @throws
     */
    @PutMapping("{postId}/{userId}/comments/{commentId}")
    public ResponseEntity<ReplyResponseDto> updateReply(@PathVariable(name = "postId")String postId,
                                                        @PathVariable(name = "userId")Long userId,
                                                        @PathVariable(name = "commentId")String commentId,
                                                        @RequestBody ReplyRequestDto replyRequestDto){
        Reply reply = replyService.updateReply(postId,commentId, userId, replyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(reply.toResponse());
    }


    //KMG
    /**
     * @author kmg
     * user가 작성한 reply 개수를 반환하는 API
     *
     * @param id
     * @return  Long
     */
    @GetMapping("api.hell-low.com/comment-management/users/{id}/count")
    public ResponseEntity<Long> myReplyCount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(replyService.getReplyCountByUserId(id));
    }

    /**
     * @author kmg
     * user가 작성한 reply의 정보를 반환하는 API
     *
     * @param id
     * @return  List<Reply>
     */
    @GetMapping("api.hell-low.com/comment-management/users/{id}")
    public ResponseEntity<List<Reply>> getMyReply(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(replyService.getMyReplys(id));
    }

}
