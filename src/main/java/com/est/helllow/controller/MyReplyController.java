package com.est.helllow.controller;

import com.est.helllow.dto.ReplyDTO;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.MyreplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyReplyController {
    @Autowired
    MyreplyService myreplyService;

    @GetMapping("/myreplys")
    public BaseResponse getMyReplys(@RequestParam String userId) {
        List<ReplyDTO> myReplys = myreplyService.getMyReplys(userId);
        return new BaseResponse<>(myReplys);
    }

}
