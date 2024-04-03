package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;

import com.est.helllow.domain.dto.PostSearchCondition;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {
    PostService postService;
    S3Service s3Service;

    public PostController(PostService postService, S3Service s3Service) {
        this.postService = postService;
        this.s3Service = s3Service;
    }

    //CJW
    /**
     * @author cjw
     * 게시물을 등록하는 API
     *
     * @return PostResponseDto : 등록한 post
     * @Exception IoException
     */
    @PostMapping("api/posts")
    public ResponseEntity<PostResponseDto> addPost(@RequestPart(value = "postRequest") PostRequestDto request,
                                                      @RequestPart(value = "img", required = false) MultipartFile file){
        try{
            String imgUrl = s3Service.uploadImg(file);
            Post newPost = postService.savePost(request,imgUrl);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newPost.toResponse());
        } catch(IOException e){
            return null;
        }
    }

    /**
     * @author cjw
     * 전체 게시물을 반환하는 API
     *
     * @return List<PostResponseDto> : 등록된 모든 post
     */
    @GetMapping("api/posts")
    public ResponseEntity<List<PostResponseDto>> findAllPosts() {
        List<PostResponseDto> postList = postService.findAll()
                .stream().map(PostResponseDto::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(postList);
    }

//    /**
//     * @author cjw
//     * 특정 게시물을 반환하는 API
//     *
//     * @return PostResponseDto : 특정 postId의 post
//     */
//    @GetMapping("api/posts/{postId}")
//    public ResponseEntity<PostResponseDto> findOnePost(@PathVariable String postId){
//        PostResponseDto post = postService.findById(postId).toResponse();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(post);
//    }

    /**
     * @author cjw
     * 게시물을 삭제하는 API
     *
     * @param postId
     * @return void
     */
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId){
        postService.delete(postId);

        return ResponseEntity.ok().build();
    }

    /**
     * @author cjw
     * 게시물을 수정하는 API
     *
     * @param postId
     * @return Post : 수정한 post
     */
    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable String postId, @RequestBody PostRequestDto request){
        Post updatedPost = postService.update(postId, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedPost);
    }

    //LSH
    /**
     * @author lsh
     * 게시물에 해당하는 댓글 리스트 조회 API
     *
     * @param postId
     * @return postRes : 검색한 post
     */
    @GetMapping("api/posts/{postId}")
    public ResponseEntity<PostRes> getPost(@PathVariable(name = "postId")String postId){
        PostRes postRes = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postRes);
    }

    /**
     * @author lsh
     * 게시물 검색 기능 API
     * 테스트 위한 구조 , 이후 변경 예정
     *
     * @return List<Post> : 검색한 post
     */
    @GetMapping("api/posts/search")
    public ResponseEntity<List<Post>> searchPost(@RequestBody PostSearchCondition postSearchCondition) {
        List<Post> posts = postService.searchPost(postSearchCondition);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    //KMG

}
