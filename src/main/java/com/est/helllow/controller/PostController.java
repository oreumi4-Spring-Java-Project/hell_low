package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostResponseDto;

import com.est.helllow.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("api/posts")
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto request){
        Post newPost = postService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newPost.toResponse());
    }

    @GetMapping("api/posts")
    public ResponseEntity<List<PostResponseDto>> findAllPosts(){
        List<PostResponseDto> postList = postService.findAll()
                .stream().map(PostResponseDto::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(postList);
    }

    @GetMapping("api/posts/{postId}")
    public ResponseEntity<PostResponseDto> findOnePost(@PathVariable Long postId){
        PostResponseDto post = postService.findById(postId).toResponse();
        return ResponseEntity.status(HttpStatus.OK)
                .body(post);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.delete(postId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto request){
        Post updatedPost = postService.update(postId, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedPost);
    }

}
