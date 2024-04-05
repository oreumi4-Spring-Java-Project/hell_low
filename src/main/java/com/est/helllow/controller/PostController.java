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
     * @return PostResponseDto : 등록한 post
     * @author cjw
     * 게시물을 등록하는 API
     * @Exception IoException
     */
    @PostMapping("api.hell-low.com/post-management/users/{id}/posts")
    public ResponseEntity<PostResponseDto> addPost(@RequestPart(value = "postRequest") PostRequestDto request,
                                                   @RequestPart(value = "img", required = false) MultipartFile file) {
        try {
            String imgUrl = s3Service.uploadImg(file);
            Post newPost = postService.savePost(request, imgUrl);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newPost.toResponse());
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @return List<PostResponseDto> : 등록된 모든 post
     * @author cjw
     * 전체 게시물을 반환하는 API
     */
    @GetMapping("api.hell-low.com/post-management/posts")
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
//    @GetMapping("api.hell-low.com/post-management/posts/{id}")
//    public ResponseEntity<PostResponseDto> findOnePost(@PathVariable String id){
//        PostResponseDto post = postService.findById(id).toResponse();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(post);
//    }

    /**
     * @param id
     * @return void
     * @author cjw
     * postId가 일치하는 게시물을 삭제하는 API
     */
    @DeleteMapping("api.hell-low.com/post-management/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        PostResponseDto post = postService.findById(id).toResponse();
        if (post.getPostFile() != null) {
            s3Service.deleteImg(post.getPostFile());
        }
        postService.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * @return Post : 수정한 post
     * @author cjw
     * 게시물을 수정하는 API
     */
    @PutMapping("api.hell-low.com/post-management/{userId}/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(name = "userId") String userId,
                                           @PathVariable(name = "postId") String postId,
                                           @RequestPart(value = "postRequest") PostRequestDto request,
                                           @RequestPart(value = "img", required = false) MultipartFile file) throws IOException {
        PostResponseDto post = postService.findById(postId).toResponse();
        String imgUrl = null;

        if (post.getPostFile() == null) { //원래 post에 이미지가 없었을 경우
            imgUrl = s3Service.uploadImg(file);
        } else { //이미지가 원래 있었을 경우
            imgUrl = s3Service.updateImg(file, post.getPostFile());
        }

        Post updatedPost = postService.update(postId, request, imgUrl);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedPost);
    }

    //LSH

    /**
     * @param id
     * @return postRes : 검색한 post
     * @author lsh
     * 게시물에 해당하는 댓글 리스트 조회 API
     */
    @GetMapping("api.hell-low.com/post-management/posts/{id}")
    public ResponseEntity<PostRes> getPost(@PathVariable(name = "postId") String id) {
        PostRes postRes = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postRes);
    }

    /**
     * @return List<Post> : 검색한 post
     * @author lsh
     * 게시물 검색 기능 API
     * 테스트 위한 구조 , 이후 변경 예정
     */
    @GetMapping("api.hell-low.com/post-management/posts-search")
    public ResponseEntity<List<PostResponseDto>> searchPost(@RequestBody PostSearchCondition postSearchCondition) {
        List<PostResponseDto> postList = postService.searchPost(postSearchCondition)
                .stream().map(PostResponseDto::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(postList);
    }

    //KMG

    /**
     * @param id
     * @return Long
     * @author kmg
     * user가 작성한 post 개수를 반환하는 API
     */
    //분할 필요
    @GetMapping("api.hell-low.com/post-management/users/{id}/count")
    public ResponseEntity<Long> myPostCount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPostCountByUserId(id));
    }

    /**
     * @param id
     * @return List<Post> : 검색한 post
     * @author kmg
     * userId가 일치하는 모든 게시물을 탐색하는 API
     */
    @GetMapping("api.hell-low.com/post-management/users/{id}")
    public ResponseEntity<List<PostResponseDto>> getMyPosts(@RequestParam Long id) {
        List<PostResponseDto> postList = postService.getMyPosts(id)
                .stream().map(PostResponseDto::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(postList);
    }
}
