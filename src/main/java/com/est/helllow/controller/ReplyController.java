package com.est.helllow.controller;


import com.est.helllow.domain.Reply;
import com.est.helllow.domain.dto.ReplyRequestDto;
import com.est.helllow.domain.dto.ReplyResponseDto;
import com.est.helllow.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{userId}/comments")
    @ResponseBody
    public ResponseEntity<ReplyResponseDto> replySave(@PathVariable(name = "postId") Long postId,
                                                      @PathVariable(name = "userId")Long userId,
                                                      @RequestBody ReplyRequestDto replyRequestDto){
        Reply reply = replyService.replySave(postId,userId,replyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReplyResponseDto(reply));
    }
//    @DeleteMapping("/{userId}/comments/{commentId}")
//    @ResponseBody
//    public ResponseEntity<Void> deleteReply(@PathVariable(name = "postId")Long postId,
//                                            @PathVariable(name = "userId")Long userId,
//                                            @PathVariable(name = "commentId")Long commentId){
//
//        replyService.deleteComment(commentId);
//        return ResponseEntity.ok().build();
//    }

}
