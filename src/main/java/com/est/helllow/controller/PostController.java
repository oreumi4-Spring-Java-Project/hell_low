package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.AddPostRequest;
import com.est.helllow.domain.dto.AddPostResponse;

import com.est.helllow.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("api/post")
    public ResponseEntity<AddPostResponse> addPost(@RequestBody AddPostRequest request){
        Post newPost = postService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newPost.toResponse());
    }


}
