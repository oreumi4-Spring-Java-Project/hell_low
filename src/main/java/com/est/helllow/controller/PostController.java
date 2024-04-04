package com.est.helllow.controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;

import com.est.helllow.domain.dto.PostSearchCondition;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class PostController {
    PostService postService;
    S3Service s3Service;

    public PostController(PostService postService, S3Service s3Service) {
        this.postService = postService;
        this.s3Service = s3Service;
    }

    /**
     * 게시물 이미지 업로드 api
     */
    @PostMapping("api.hell-low.com/post-management/users/{id}")
    public BaseResponse addPost(@PathVariable(name = "id") String userId,
                                @RequestPart(value = "postRequest") PostRequestDto request,
                                @RequestPart(value = "img", required = false) MultipartFile file) {
        try {
            String imgUrl = s3Service.uploadImg(file);
            Post newPost = postService.savePost(userId, request, imgUrl);
            return new BaseResponse<>(newPost);
        } catch (IOException exception) {
            return new BaseResponse(BaseExceptionCode.NOT_EXIST_IMG);
        } catch (BaseException exception) {
            return new BaseResponse(exception.getExceptionCode());
        }
    }

    /**
     * 상세 게시물 페이지 조회 API
     */
    @GetMapping("api.hell-low.com/post-management/posts")
    public BaseResponse findAllPosts() {
        List<PostResponseDto> postList = postService.findAll()
                .stream().map(PostResponseDto::new)
                .toList();
        return new BaseResponse(postList);
    }

//    @GetMapping("api/posts/{postId}")
//    public ResponseEntity<PostResponseDto> findOnePost(@PathVariable Long postId){
//        PostResponseDto post = postService.findById(postId).toResponse();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(post);
//    }

    /**
     * 게시물에 해당하는 댓글 리스트 조회 API
     *
     * @param postId
     * @return
     * @throws
     */
    @GetMapping("api.hell-low.com/post-management/posts/{id}")
    public BaseResponse getPost(@PathVariable(name = "id") String postId) {
        try {
            PostRes postRes = postService.getPost(postId);
            return new BaseResponse<>(postRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    /**
     * 게시물 검색 기능 API
     * 테스트 위한 구조 , 이후 변경 예정
     *
     * @return
     */
    @GetMapping("api.hell-low.com/post-management/posts-search")
    public BaseResponse searchPost(@RequestBody PostSearchCondition postSearchCondition) {
        List<Post> posts = postService.searchPost(postSearchCondition);
        return new BaseResponse<>(posts);
    }

    /**
     * 게시물에 삭제 API
     *
     * @param postId
     * @return
     * @throws
     */
    @DeleteMapping("api.hell-low.com/post-management/posts/{id}")
    public BaseResponse deletePost(@PathVariable(name = "id") String postId) {
        try {
            postService.delete(postId);
            return new BaseResponse<>(postId + "번 게시물이 삭제되었습니다");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    /**
     * 게시물 수정 API
     *
     * @param postId
     * @return
     * @throws
     */
    @PutMapping("api.hell-low.com/post-management/posts/{id}")
    public BaseResponse updatePost(@PathVariable String postId, @RequestBody PostRequestDto request) {
        try {
            Post updatedPost = postService.update(postId, request);
            PostResponseDto response = updatedPost.toResponse();
            return new BaseResponse<>(response);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }
}
