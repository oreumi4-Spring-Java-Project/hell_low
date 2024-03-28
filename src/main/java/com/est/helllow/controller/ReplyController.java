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
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/post/{postId}/replies")
    public ResponseEntity<ReplyResponseDto> replySave(@PathVariable(name = "postId") Long postId, @RequestBody ReplyRequestDto replyRequestDto){
        Reply reply = replyService.replySave(postId, replyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReplyResponseDto(reply));

    }
}
