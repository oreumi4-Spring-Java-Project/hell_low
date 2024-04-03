package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.dto.PostDTO;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.MyworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyworkController {
    @Autowired
    MyworkService myworkService;

    @GetMapping("/myposts")
    public BaseResponse getMyPosts(@RequestParam String userId) {
        List<PostDTO> myPosts = myworkService.getMyPosts(userId);
        return new BaseResponse<>(myPosts);
    }
}
