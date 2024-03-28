package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.dto.PostDTO;
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
    public List<PostDTO> getMyPosts(@RequestParam String userId) {
        return myworkService.getMyPosts(Long.parseLong(userId));
    }
}
