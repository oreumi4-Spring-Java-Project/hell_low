package com.est.helllow.controller;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;

import com.est.helllow.domain.dto.PostSearchCondition;
import com.est.helllow.dto.ResponseDTO;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.exception.BaseResponse;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {
    PostService postService;
    S3Service s3Service;

    public PostController(PostService postService, S3Service s3Service) {
        this.postService = postService;
        this.s3Service = s3Service;
    }

    /**
     * 게시물을 등록하는 API
     *
     * @return PostResponseDto : 등록한 post
     * @author cjw
     */
    @PostMapping("api.hell-low.com/post-management/{userId}")
    public BaseResponse addPost(@PathVariable(name = "userId") String userId,
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
     * 전체 게시물을 반환하는 API
     *
     * @return List<PostResponseDto> : 등록된 모든 post
     * @author cjw
     */
    @GetMapping("api.hell-low.com/post-management/posts")
    public BaseResponse findAllPosts() {
        List<PostResponseDto> postList = postService.findAll()
                .stream().map(PostResponseDto::new)
                .toList();
        return new BaseResponse(postList);
    }

    /**
     * postId가 일치하는 게시물을 삭제하는 API
     *
     * @return void
     * @author cjw
     */
    @DeleteMapping("api.hell-low.com/post-management/{userId}/{postId}")
    public BaseResponse deletePost(@PathVariable(name = "userId") String userId,
                                   @PathVariable(name = "postId") String postId) {
        try {
            PostResponseDto post = postService.findById(postId).toResponse();
            if(post.getPostFile() != null){
                s3Service.deleteImg(post.getPostFile());
            }
            postService.delete(postId);
            return new BaseResponse<>(postId + "번 게시물이 삭제되었습니다");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    /**
     * 게시물을 수정하는 API
     *
     * @return Post : 수정한 post
     * @author cjw
     */
    @PutMapping("api.hell-low.com/post-management/{userId}/{postId}")
    public BaseResponse updatePost(@PathVariable(name = "userId") String userId,
                                   @PathVariable(name = "postId") String postId,
                                   @RequestPart(value = "postRequest") PostRequestDto request,
                                   @RequestPart(value = "img", required = false) MultipartFile file) throws IOException{
        try {
            PostResponseDto post = postService.findById(postId).toResponse();
            String imgUrl = null;

            if (post.getPostFile() == null) { //원래 post에 이미지가 없었을 경우
                imgUrl = s3Service.uploadImg(file);
            } else { //이미지가 원래 있었을 경우
                imgUrl = s3Service.updateImg(file, post.getPostFile());
            }

            Post updatedPost = postService.update(postId, request, imgUrl);
            PostResponseDto response = updatedPost.toResponse();
            return new BaseResponse<>(response);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

    /**
     * 게시물개수를 반환하는 API
     *
     * @return Long
     * @author cjw
     */
    @GetMapping("api.hell-low.com/post-management/count")
    public BaseResponse countPosts() {
        try {
            Long postCount = postService.getPostCount();
            return new BaseResponse<>(postCount);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getExceptionCode());
        }
    }

//    /**
//     * @author cjw
//     * 특정 게시물정보만 반환하는 API
//     *
//     * @return PostResponseDto : 특정 postId의 post
//     */
//    @GetMapping("api.hell-low.com/post-management/posts/{id}")
//    public ResponseEntity<PostResponseDto> findOnePost(@PathVariable String id){
//        PostResponseDto post = postService.findById(id).toResponse();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(post);
//    }

    //LSH

    /**
     * 게시물에 해당하는 댓글 리스트 조회 API
     *
     * @param postId
     * @return postRes : 검색한 post
     * @author lsh
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
     *
     * @return List<Post> : 검색한 post
     * @author lsh
     * 테스트 위한 구조 , 이후 변경 예정
     */
    @GetMapping("api.hell-low.com/post-management/posts-search")
    public BaseResponse searchPost(@RequestParam(value = "searchText") PostSearchCondition postSearchCondition) {
        List<PostResponseDto> posts = postService.searchPost(postSearchCondition);
        return new BaseResponse<>(posts);
    }

    //KMG

    /**
     * user가 작성한 post 개수를 반환하는 API
     *
     * @param userId
     * @return Long
     * @author kmg
     */
    @GetMapping("api.hell-low.com/post-management/users/{id}/count")
    public BaseResponse mypostcount(@PathVariable(name = "id") String userId) {
        Long postCount = postService.getPostCountByUserId(userId);
        return new BaseResponse(postCount);
    }

    /**
     * userId가 일치하는 모든 게시물을 탐색하는 API
     *
     * @param userId
     * @return List<Post> : 검색한 post
     * @author kmg
     */

    @GetMapping("api.hell-low.com/post-management/users/{id}")
    public BaseResponse getMyPosts(@PathVariable(name = "id") String userId) {
        List<PostResponseDto> postList = postService.getMyPosts(userId)
                .stream().map(PostResponseDto::new)
                .toList();
        return new BaseResponse<>(postList);
    }

}
